# User Guide

Wqchat is a chatbot that helps the user to keep track of various tasks, optimized for use via a Command Line Interface (CLI).
## Features 

### View task list: list

View the list of tasks added.

Format: `list`

### Add a todo task: todo

Add a todo task in the list of tasks.

Format: `todo [TASK_DESCRIPTION]`

Examples: `todo read user guide`

### Add a deadline: deadline

Add a deadline in the list of tasks.

Format: `deadline [TASK_DESCRIPTION] [/by DUE_TIME]`

Examples: `deadline do quiz /by today 2359`

### Add an event: event

Add an event in the list of tasks.

Format: `event [TASK_DESCRIPTION] [/from STARTING_TIME] [/to END_TIME]`

Example: `event class /from 1pm /to 2pm`

### Mark a task: mark

Mark a task as done.

Format: `mark [INDEX]`

Example: `mark 5`

* Mark the 5th task in the list as done.

### Unmark a task: unmark

Mark a task as not done.

Format: `unmark [INDEX]`

Example: `unmark 5`

* Mark the 5th task in the list as not done.

### Delete a task: delete

Format: `delete [INDEX]`

Example: `delete 5`
* Delete the 5th task from the list

### Find a task: find

Search for tasks with a keyword.

Format: `find [KEYWORD]`

Example: `find class`
* Return all tasks that contains the keyword "class".