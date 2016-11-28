package app;

import strings.Request;
import strings.Attributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Servlet for managing persons. Performs adding, editing and deleting of persons via
 * doGet and doPost methods. Contains {@code Phonebook} link to perform that operations.
 */
public class ManagePersonServlet extends HttpServlet {
    private static final String DISPATCHER_MANAGE = "/ManagePerson.jsp";
    private static final String DISPATCHER_LIST = "/List.jsp";

    private static final long serialVersionUID = 1L;
    private Phonebook phonebook;

    public ManagePersonServlet() {
        super();
        try {
            this.phonebook = Phonebook.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute(Attributes.PHONEBOOK, this.phonebook);
        HashMap<String, String> jspParameters = new HashMap<>();

        RequestDispatcher dispatcherForManagerPerson = request.getRequestDispatcher(DISPATCHER_MANAGE);
        RequestDispatcher dispatcherForList = request.getRequestDispatcher(DISPATCHER_LIST);
        String action = request.getParameter(Request.ACTION);
        String id = request.getParameter(Request.ID);

        // If id and action weren't specified, just show persons list
        if ((action == null) && (id == null)) {
            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            dispatcherForList.forward(request, response);
        } else {
            switch (action) {
                case Attributes.ACTION_ADD_PERSON:
                    actionAddPerson(request, jspParameters);
                    dispatcherForManagerPerson.forward(request, response);
                    break;
                case Attributes.ACTION_EDIT_PERSON:
                    actionEditPerson(request, jspParameters, id);
                    dispatcherForManagerPerson.forward(request, response);
                    break;
                case Attributes.ACTION_DELETE_PERSON:
                    actionDeletePerson(request, jspParameters, id);
                    dispatcherForList.forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute(Attributes.PHONEBOOK, this.phonebook);
        HashMap<String, String> jspParameters = new HashMap<>();

        RequestDispatcher dispatcherForManager = request.getRequestDispatcher(DISPATCHER_MANAGE);
        RequestDispatcher dispatcherForList = request.getRequestDispatcher(DISPATCHER_LIST);
        String addGo = request.getParameter(Attributes.ACTION_NEXT_ADD_PERSON);
        String editGo = request.getParameter(Attributes.ACTION_NEXT_EDIT_PERSON);
        String errorMessage = "";

        if (addGo != null) {
            actionAddPersonSubmit(request, response, jspParameters, dispatcherForList, dispatcherForManager);
        }
        if (editGo != null) {
            actionEditPersonSubmit(request, response, jspParameters, dispatcherForList, dispatcherForManager);
        }
    }

    /**
     * Sets attributes for request to add new person.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionAddPerson(HttpServletRequest request, HashMap<String, String> jspParameters) {
        Person emptyPerson = new Person();
        jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_ADD_PERSON);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_ADD_PERSON);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_ADD);
        request.setAttribute(Attributes.PERSON, emptyPerson);
        request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
    }

    /**
     * Sets attributes for request to edit person.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionEditPerson(HttpServletRequest request, HashMap<String, String> jspParameters, String id) {
        Person editablePerson = this.phonebook.getPerson(id);
        jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_EDIT_PERSON);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_EDIT_PERSON);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_EDIT);
        request.setAttribute(Attributes.PERSON, editablePerson);
        request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
    }

    /**
     * Sets attributes for request about success of person deleting. Invokes phonebook method to delete
     * person entry from database.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionDeletePerson(HttpServletRequest request, HashMap<String, String> jspParameters, String id) {
        if (phonebook.deletePerson(id)) {
            jspParameters.put(Attributes.PARAM_ACTION_RESULT, "DELETION_SUCCESS");
            jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Удаление выполнено успешно");
        } else {
            jspParameters.put(Attributes.PARAM_ACTION_RESULT, "DELETION_FAILURE");
            jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Ошибка удаления (возможно, запись не " +
                    "найдена)");
        }
        request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
    }

    /**
     * Sets attributes for request about success of person adding. Invokes phonebook method to add
     * person entry to database.
     *  @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionAddPersonSubmit(HttpServletRequest request,
                                       HttpServletResponse response,
                                       HashMap<String, String> jspParameters,
                                       RequestDispatcher dispatcherForList,
                                       RequestDispatcher dispatcherForManager) throws ServletException, IOException {

        Person newPerson = new Person(
                request.getParameter(Request.NAME),
                request.getParameter(Request.SURNAME),
                request.getParameter(Request.MIDDLENAME)
        );

        String errorMessage = new Validator().validatePersonFMLName(newPerson);
        if (errorMessage.equals("")) {

            if (this.phonebook.addPerson(newPerson)) {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "ADDITION_SUCCESS");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Добавление выполнено успешно");
            } else {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "ADDITION_FAILURE");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Ошибка добавления");
            }

            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            dispatcherForList.forward(request, response);
        } else {
            jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_ADD_PERSON);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_ADD_PERSON);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_ADD);
            jspParameters.put(Attributes.ERROR_MSG, errorMessage);

            request.setAttribute(Attributes.PERSON, newPerson);
            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);

            dispatcherForManager.forward(request, response);
        }
    }

    /**
     * Sets attributes for request about success of person editing. Invokes phonebook method to edit
     * person entry in database.
     *  @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionEditPersonSubmit(HttpServletRequest request,
                                        HttpServletResponse response,
                                        HashMap<String, String> jspParameters,
                                        RequestDispatcher dispatcherForList,
                                        RequestDispatcher dispatcherForManager) throws ServletException, IOException {
        Person updatablePerson = this.phonebook.getPerson(request.getParameter(Request.ID));
        updatablePerson.setName(request.getParameter(Request.NAME));
        updatablePerson.setSurname(request.getParameter(Request.SURNAME));
        updatablePerson.setMiddlename(request.getParameter(Request.MIDDLENAME));

        String errorMessage = new Validator().validatePersonFMLName(updatablePerson);
        if (errorMessage.equals("")) {
            if (this.phonebook.updatePerson(updatablePerson)) {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "UPDATE_SUCCESS");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Обновление выполнено успешно");
            } else {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "UPDATE_FAILURE");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Ошибка обновления");
            }

            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            dispatcherForList.forward(request, response);
        } else {
            jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_EDIT_PERSON);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_EDIT_PERSON);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_EDIT);
            jspParameters.put(Attributes.ERROR_MSG, errorMessage);

            request.setAttribute(Attributes.PERSON, updatablePerson);
            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);

            dispatcherForManager.forward(request, response);
        }
    }
}
