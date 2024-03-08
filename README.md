# Wqchat

Wqchat is a chatbot that helps the user to keep track of various tasks, optimized for use via a Command Line Interface (CLI). Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
____________________________________________________________
Hello! I'm Wqchat
What can I do for you?
____________________________________________________________
```

## User Guide

Wqchat is a chatbot that helps the user to keep track of various tasks, optimized for use via a Command Line Interface (CLI).
### Features 

#### View task list: list

View the list of tasks added.

Format: `list`

#### Add a todo task: todo

Add a todo task in the list of tasks.

Format: `todo [TASK_DESCRIPTION]`

Examples: `todo read user guide`

#### Add a deadline: deadline

Add a deadline in the list of tasks.

Format: `deadline [TASK_DESCRIPTION] [/by DUE_TIME]`

Examples: `deadline do quiz /by today 2359`

#### Add an event: event

Add an event in the list of tasks.

Format: `event [TASK_DESCRIPTION] [/from STARTING_TIME] [/to END_TIME]`

Example: `event class /from 1pm /to 2pm`

#### Mark a task: mark

Mark a task as done.

Format: `mark [INDEX]`

Example: `mark 5`

* Mark the 5th task in the list as done.

#### Unmark a task: unmark

Mark a task as not done.

Format: `unmark [INDEX]`

Example: `unmark 5`

* Mark the 5th task in the list as not done.

#### Delete a task: delete

Format: `delete [INDEX]`

Example: `delete 5`
* Delete the 5th task from the list

#### Find a task: find

Search for tasks with a keyword.

Format: `find [KEYWORD]`

Example: `find class`
* Return all tasks that contains the keyword "class".
