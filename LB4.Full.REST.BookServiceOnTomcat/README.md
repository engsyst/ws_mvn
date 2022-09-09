# Steps to create a project in IntelliJ IDEA Community Edition

## Importing project

1. Open IntelliJ IDEA
2. File > New > Project from Existing Sources...
3. Pick up pom.xml in the tree > Ok
4. In the dialog: Import Maven Project, check up:
	- [+] Search for project recursively
	- [+] Import project automatically
	
	Automatically download
	- [+] Documentation
	- [?] Sources
    
    Press: *Next*

5. Follow wizard and check up all CheckBoxes
6. In the last dialog type: \<Your Project Name\>
7. Press: *Finish*

---
See: [Maven Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

See: [Cargo maven plugin goals](https://codehaus-cargo.github.io/cargo/Maven2+Plugin+Getting+Started.html)

---

## Running project

1. Press: Add configuration

2. In the dialog "Run/Debug Configuration":
    - Press: [+]
    - Choice: *Maven*
    - In the "Command Line" type: *cargo:run*  
    (*this will run project in the embedded Tomcat*)

    1. **In the "Before lunch" group of this window press**: [+] 
        - Choice: *Run Maven Goal*
        - Type: *package*
        - Press: *Ok*

3. Ok

Enjoy

P.S.
--------------
Any changes, except JSP, require restarting the application.
