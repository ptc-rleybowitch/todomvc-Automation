package ui.tests.todomvc.actions;

import org.testng.Assert;
import ui.tests.todomvc.pages.TodoHomePage;
import ui.tests.todomvc.pages.TodoTodoListPage;
import utils.webdriverWrapper.DriverWrapper;

public class TodoListActions extends ActionsBase {

    public enum ToDoState{
        DONE,TODO;
        @Override
        public String toString(){
            return this.name();
        }
    }

    private TodoHomePage todomvcHomePage;
    private TodoTodoListPage todomvcTodoListPage;

    public TodoListActions(DriverWrapper driver) {
        super(driver);
    }

    protected void initPages(DriverWrapper driver) {
        logger.trace("starting pages");
        todomvcHomePage = new TodoHomePage(driver);
        todomvcTodoListPage = new TodoTodoListPage(driver);
    }

    public void goToTodoListPage(){
        logger.debug("Navigate to the todo list page");
        todomvcHomePage.getPage();
        todomvcTodoListPage.waitForPageLoad();
    }
    public void addNewTodo(String todoName) {
        logger.debug("Adding todo task named : "+todoName);
        todomvcTodoListPage.setNewTodoName(todoName);
        todomvcTodoListPage.approveOnNewTodo();
    }

    public void verifyLastTodo(String todoName) {
        logger.debug("Verify todo task named : "+todoName +" is the last one on the list");
        String actualLastTodo = todomvcTodoListPage.getLastTodoText();

        Assert.assertNotNull(actualLastTodo,"No todo ware found in tha list ");
        Assert.assertEquals(actualLastTodo,todoName,"Last todo was not as expected");
    }

    public void toggleToDo(String todoName) {
        logger.debug("toggle todo named : "+todoName);
        todomvcTodoListPage.toggleTodo(todoName);
    }

    public void verifyToDoState(String todoName, ToDoState exactedState) {
        logger.debug("verify that todo  named : "+todoName+ "is at state "+exactedState );
       String actualStatus =  todomvcTodoListPage.getTodoStatus(todoName);
       String expected ="" ;
       if (exactedState.equals(ToDoState.DONE)){
           expected = "completed";
       }
       Assert.assertEquals(actualStatus,expected,"Todo State was not as expected");
    }
}
