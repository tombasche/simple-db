## Simple-db (kdb)

### TODO

* How to fit rows into a tree structure for effective insertion + retrieval?
* Each row should have an internal 'rowNum' which is separate from id used to identify the row
* ID should be generated as a monotonically increasing integer similar to the mongodb object id
  * Replace user specified ids with this


### Commands

* insert
* select
* top 
  * syntax:
    ```
    top <int> <tableName> <criteria> <asc|desc>
    ```
  * e.g. `top 5 users name decs` would return top 5 user names sorted descending
* delete 
  * delete users name=tom

### Meta-commands

* /exit
* /clear
