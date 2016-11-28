package app;

import strings.Attributes;
import strings.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Servlet for managing phones. Performs adding, editing and deleting of phones via
 * doGet and doPost methods.
 * Contains {@code Phonebook} link to perform that operations.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 27.11.2016
 */
public class ManagePhoneServlet extends HttpServlet {
    private static final String DISPATCHER_MANAGE = "/ManagePhone.jsp";

    private static final long serialVersionUID = 1L;
    private Phonebook phonebook;

    public ManagePhoneServlet() {
        super();
        try {
            this.phonebook = Phonebook.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute(Attributes.PHONEBOOK, this.phonebook);
        HashMap<String, String> jspParameters = new HashMap<>();

        RequestDispatcher dispatcherForManagePhone = request.getRequestDispatcher(DISPATCHER_MANAGE);

        String action = request.getParameter(Request.ACTION);
        String id = request.getParameter(Request.ID);
        String ownerId = request.getParameter(Request.OWNER_ID);

        // If id and action weren't specified, just show person's info
        if ((action == null) && (ownerId == null)) {
            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            dispatcherForManagePhone.forward(request, response);
        } else {
            switch (action) {
                case Attributes.ACTION_ADD_PHONE:
                    actionAddPhone(request, jspParameters);
                    dispatcherForManagePhone.forward(request, response);
                    break;
                case Attributes.ACTION_EDIT_PHONE:
                    actionEditPhone(request, jspParameters, id, ownerId);
                    dispatcherForManagePhone.forward(request, response);
                    break;
                case Attributes.ACTION_DELETE_PHONE:
                    actionDeletePhone(request, jspParameters, id, ownerId);
                    response.sendRedirect(request.getContextPath() + "/?action=" +
                            Attributes.ACTION_EDIT_PERSON + "&id=" + ownerId);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute(Attributes.PHONEBOOK, this.phonebook);
        HashMap<String, String> jspParameters = new HashMap<>();

        RequestDispatcher dispatcherForManagePhone = request.getRequestDispatcher(DISPATCHER_MANAGE);
        String addPhoneGo = request.getParameter(Attributes.ACTION_NEXT_ADD_PHONE);
        String editPhoneGo = request.getParameter(Attributes.ACTION_NEXT_EDIT_PHONE);
        String errorMessage = "";

        if (addPhoneGo != null) {
            actionAddPhoneSubmit(request, response, jspParameters, dispatcherForManagePhone);
        }
        if (editPhoneGo != null) {
            actionEditPhoneSubmit(request, response, jspParameters, dispatcherForManagePhone);
        }
    }

    /**
     * Sets attributes for request to add new phone.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionAddPhone(HttpServletRequest request,
                                HashMap<String, String> jspParameters) {
        Phone emptyPhone = new Phone();
        jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_ADD_PHONE);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_ADD_PHONE);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_ADD);
        request.setAttribute(Attributes.PHONE, emptyPhone);
        request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
    }

    /**
     * Sets attributes for request to edit phone.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionEditPhone(HttpServletRequest request,
                                 HashMap<String, String> jspParameters,
                                 String id,
                                 String ownerId) {
        Phone editablePhone = this.phonebook.getPerson(ownerId).getPhones().get(id);
        jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_EDIT_PHONE);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_EDIT_PHONE);
        jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_EDIT);
        request.setAttribute(Attributes.PHONE, editablePhone);
        request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
    }

    /**
     * Sets attributes for request about success of phone deleting. Invokes phonebook method to delete
     * phone entry from database.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionDeletePhone(HttpServletRequest request,
                                   HashMap<String, String> jspParameters,
                                   String id,
                                   String ownerId) {
        if (phonebook.deletePhone(id, ownerId)) {
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
     * Sets attributes for request about success of phone adding. Invokes phonebook method to add
     * phone entry to database.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionAddPhoneSubmit(HttpServletRequest request,
                                      HttpServletResponse response,
                                      HashMap<String, String> jspParameters,
                                      RequestDispatcher dispatcherForManagePhone)
            throws IOException, ServletException {
        Phone newPhone = new Phone(
                request.getParameter(Request.OWNER_ID),
                request.getParameter(Request.NUMBER)
        );

        String errorMessage = new Validator().validateNumber(newPhone);

        if (errorMessage.equals("")) {
            if (this.phonebook.addPhone(newPhone)) {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "ADDITION_SUCCESS");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Добавление выполнено успешно");
            } else {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "ADDITION_FAILURE");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Ошибка добавления");
            }

            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            response.sendRedirect(request.getContextPath() + "/?action=" +
                    Attributes.ACTION_EDIT_PERSON + "&id=" +
                    newPhone.getOwnerId());
        }

        // If error occurred, then show adding form again
        else {
            jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_ADD_PHONE);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_ADD_PHONE);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_ADD);
            jspParameters.put(Attributes.ERROR_MSG, errorMessage);

            request.setAttribute(Attributes.PHONE, new Phone());
            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            dispatcherForManagePhone.forward(request, response);
        }
    }

    /**
     * Sets attributes for request about success of phone editing. Invokes phonebook method to edit
     * phone entry in database.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionEditPhoneSubmit(HttpServletRequest request,
                                       HttpServletResponse response,
                                       HashMap<String, String> jspParameters,
                                       RequestDispatcher dispatcherForManagePhone)
            throws IOException, ServletException {
        Phone updatablePhone = this.phonebook.getPhone(
                request.getParameter(Request.OWNER_ID),
                request.getParameter(Request.ID)
        );
        updatablePhone.setNumber(request.getParameter(Request.NUMBER));
        String errorMessage = new Validator().validateNumber(updatablePhone);

        if (errorMessage.equals("")) {
            if (this.phonebook.updatePhone(updatablePhone)) {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "UPDATE_SUCCESS");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Обновление выполнено успешно");
            } else {
                jspParameters.put(Attributes.PARAM_ACTION_RESULT, "UPDATE_FAILURE");
                jspParameters.put(Attributes.PARAM_ACTION_RESULT_LABEL, "Ошибка обновления");
            }

            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            response.sendRedirect(request.getContextPath() + "/?action=" +
                    Attributes.ACTION_EDIT_PERSON + "&id=" +
                    updatablePhone.getOwnerId());
        } else {
            jspParameters.put(Attributes.PARAM_ACTION, Attributes.ACTION_EDIT_PHONE);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION, Attributes.ACTION_NEXT_EDIT_PHONE);
            jspParameters.put(Attributes.PARAM_NEXT_ACTION_LABEL, Attributes.LABEL_EDIT);
            jspParameters.put(Attributes.ERROR_MSG, errorMessage);

            request.setAttribute(Attributes.PHONE, updatablePhone);
            request.setAttribute(Attributes.JSP_PARAMETERS, jspParameters);
            dispatcherForManagePhone.forward(request, response);
        }
    }
}