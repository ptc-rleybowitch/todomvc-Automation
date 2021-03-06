package ui.tests.todomvc.tests.todolist;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ui.tests.todomvc.actions.TodoListActions;
import ui.tests.todomvc.tests.BaseTest;

public class MarkTodoAsDone extends BaseTest {

    private String todoName = "My Awesome ToBeDone";

    @Description("This test create a new Todo task and mark it as done and then as un done")

    @Test(description = "Adding a new Task")
    public void addNewTask(){
        todoListActions.goToTodoListPage();
        todoListActions.addNewTodo(todoName);
        todoListActions.verifyLastTodo(todoName);
        todoListActions.verifyToDoState(todoName,TodoListActions.ToDoState.TODO);
    }
    @Test(description = "Mark task as done",dependsOnMethods = "addNewTask")
    public void markTodoAsDone(){
        todoListActions.toggleToDo(todoName);
        todoListActions.verifyToDoState(todoName,TodoListActions.ToDoState.DONE);
    }
    @Test(description = "Undo mark task as done",dependsOnMethods = "markTodoAsDone")
    public void unMarkTodoAsDone(){
        todoListActions.toggleToDo(todoName);
        todoListActions.verifyToDoState(todoName,TodoListActions.ToDoState.TODO);
    }
}
