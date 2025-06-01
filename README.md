# E-Learning Project (Gradle)

Acest proiect reprezinta un sistem de e-learning implementat in Java. Sistemul permite gestionarea cursurilor, utilizatorilor, progresului studentilor si al profesorilor, precum si interactiuni de baza intre acestia. Proiectul include functionalitati precum inscrierea in cursuri, actualizarea progresului si gestionarea cursurilor de catre profesori.

## Structura Proiectului pentru etapa 1

### Clasele principale:
1. **User**  
   Clasa abstracta care reprezinta un utilizator general. Aceasta contine informatii despre numele, username-ul, email-ul si parola utilizatorului, impreuna cu metode pentru gestionarea parolelor si obtinerea detaliilor utilizatorului.

2. **Student**  
   Clasa care extinde clasa `User` si reprezinta un student. Aceasta include informatii despre cursurile la care studentul este inscris si progresul sau in cadrul acestora. Permite inscrierea in cursuri si actualizarea progresului.
Aceasta clasa pastreaza cursurile sub forma unui ***TreeSet***, factorul care determina comparatia fiind definit in clasa, un Comparator ce ordoneaza lexicografic titlurile cursurilor.
3. **Teacher**  
   Clasa care extinde clasa `User` si reprezinta un profesor. Aceasta permite profesorului sa adauge sau sa elimine cursuri, avand un set de cursuri proprii.

4. **Course**  
   Clasa care reprezinta un curs, incluzand titlul si descrierea cursului. Fiecare curs poate fi asociat cu mai multi studenti si profesori.

5. **CourseProgress**  
   Clasa care stocheaza progresul unui student intr-un curs, inclusiv procentajul de completare al cursului.

6. **Quiz**  
   Clasa care reprezinta un quiz (test) asociat unui curs. Contine intrebari si raspunsuri corespunzatoare, precum si functionalitati pentru adaugarea de intrebari noi si vizualizarea detaliilor quiz-ului.

7. **Classroom**  
   Clasa care reprezinta o sala de clasa si care este asociata cu un profesor si un set de studenti. Permite gestionarea studentilor care sunt inrolati intr-o anumita clasa.

### Interfete și Implementari ale Serviciilor:
1. **StudentService**  
   Interfata care defineste operatiile permise pentru studenti, cum ar fi inscrierea unui student intr-un curs si actualizarea progresului acestuia.

2. **StudentServiceImpl**  
   Implementarea interfetei `StudentService`. Ofera functionalitati precum inscrierea unui student la un curs si actualizarea progresului acestuia in cadrul cursurilor.

3. **TeacherService**  
   Interfata care defineste operatiile permise pentru profesori, cum ar fi adaugarea si eliminarea cursurilor.

4. **TeacherServiceImpl**  
   Implementarea interfetei `TeacherService`. Permite profesorilor sa gestioneze cursurile lor (adica sa le adauge sau sa le elimine).

5. **QuizService**  
   Interfata care defineste operatiile permise pentru quiz-uri, cum ar fi adaugarea de intrebari si afisarea detaliilor unui quiz.

6. **QuizServiceImpl**  
   Implementarea interfetei `QuizService`. Permite gestionarea intrebarii unui quiz si afisarea detaliilor acestuia.

7. **ClassroomService**  
   Interfata care defineste operatiile permise pentru gestionarea salilor de clasa, cum ar fi adaugarea studentilor la o sala de clasa si vizualizarea detaliilor acesteia.

8. **ClassroomServiceImpl**  
   Implementarea interfetei `ClassroomService`. Permite gestionarea studentilor si detaliilor salilor de clasa.

#### !! Implementarea serviciilor ca singleton este aleasa pentru a asigura ca exista o singura instanta a fiecarui serviciu pe intreaga durata de viata a aplicattei, economisind astfel resurse si evitand instantierea repetata a acelorasi obiecte in timpul executiei.

# Functionalitati/interogari posibile ale Serviciilor

1. **Enroll Student in Course (StudentService)**  
   Studentii pot fi inscrisi la cursuri. Metoda `enrollStudentInCourse` din `StudentService` permite adaugarea unui curs la lista cursurilor la care studentul este inscris, daca nu este deja inscris la acel curs.

2. **Update Progress (StudentService)**  
   Progresul unui student intr-un curs poate fi actualizat. Prin metoda `updateProgress`, se poate seta procentajul de completare pentru un anumit curs, astfel incat progresul studentului sa fie reflectat corect.

3. **Print Enrolled Courses Details (StudentService)**  
   Metoda `printEnrolledCoursesDetails` afiseaza toate cursurile la care studentul este inscris. Fiecare curs va fi afisat impreuna cu detaliile acestuia, precum titlul si descrierea.

4. **Print Progress for Courses (StudentService)**  
   Prin metoda `printProgressForCourses`, studentii isi pot vizualiza progresul pe fiecare curs la care sunt inscrisi, inclusiv procentul de completare. Acesta este un mod de a verifica stadiul lor in cadrul fiecarui curs.

5. **Add Course (TeacherService)**  
   Profesorii pot adauga cursuri noi in portofoliul lor. Metoda `addCourse` din `TeacherService` le permite acestora sa isi construiasca sau extinda lista de cursuri, astfel incat sa poata invata alti studenti.

6. **Remove Course (TeacherService)**  
   Profesorii au posibilitatea de a elimina cursuri din lista lor. Prin metoda `removeCourse`, un profesor poate sterge un curs existent pe care nu mai doreste sa-l ofere studentilor.

7. **Print Courses (TeacherService)**  
   Metoda `printCourses` permite profesorilor sa vizualizeze toate cursurile pe care le detin. Aceasta metoda afiseaza toate cursurile pe care profesorul le gestioneaza si le poate oferi studentilor.

8. **Add Question to Quiz (QuizService)**  
   Quiz-urile pot avea intrebari adaugate. Prin metoda `addQuestion`, un profesor poate adauga o intrebare noua la un quiz existent, cu raspunsul corespunzator.

9. **Print Quiz Details (QuizService)**  
   Metoda `printQuizDetails` afiseaza toate intrebarile unui quiz, impreuna cu raspunsurile corecte asociate acestora.

10. **Add Student to Classroom (ClassroomService)**  
    Studentii pot fi adaugati intr-o sala de clasa. Prin metoda `addStudentToClass`, un student poate fi adaugat unei sali de clasa, iar aceasta actiune poate fi repetata pentru mai multi studenti.

11. **Print Classroom Details (ClassroomService)**  
    Metoda `printClassroomDetails` afiseaza detaliile unei sali de clasa, inclusiv lista studentilor si a profesorului care o conduce.

# Cum Functioneaza:

- **User** este o clasa de baza pentru toti utilizatorii sistemului. Ea contine informatii de baza despre utilizator si metode pentru gestionarea parolei.
- **Student** si **Teacher** sunt clase care extind `User`, fiecare avand functionalitati specifice: studentii pot sa se inscrie la cursuri si sa isi urmareasca progresul, iar profesorii pot sa adauge sau sa elimine cursuri din lista lor de cursuri.
- **Course** reprezinta un curs de invatare care poate fi atribuit studentilor si poate fi asociat cu profesori.
- **CourseProgress** tine evidenta progresului unui student intr-un curs (de exemplu, procentul de completare al cursului).
- **Quiz** permite profesorilor sa creeze teste (quiz-uri) cu intrebari si raspunsuri, iar studentii pot vizualiza intrebarile acestora si raspunsurile corecte.
- **Classroom** reprezinta o sala de clasa in care profesorii pot adauga studenti, iar acestia pot interactiona in cadrul clasei.
- **StudentService** si **TeacherService** sunt interfetele care ofera operatiile de manipulare a studentilor si profesorilor. Implementarile acestora permit gestionarea cursurilor, progresului si alte operatiuni.
- **QuizService** si **ClassroomService** permit gestionarea quiz-urilor si a salilor de clasa, oferind functionalitati suplimentare pentru interactiunea intre studenti si profesori.

# Persistenta si Servicii cu JDBC - Etapa 2

In aceasta etapa a proiectului E-Learning, s-a adaugat persistenta datelor folosind o baza de date relationala (PostgreSQL) si JDBC. S-au implementat servicii pentru operatii CRUD (create, read, update, delete) pentru mai multe entitati, precum si un serviciu de audit care inregistreaza toate actiunile importante intr-un fisier CSV.

## Conexiunea la baza de date

Conexiunea se realizeaza prin clasa `DBConnection`, un singleton care deschide conexiunea la baza de date PostgreSQL folosind urmatoarele detalii:

- Nume baza de date: `ELearningDB`
- Username: `postgres`
- Parola: `password`
- Port: `5432`

## Crearea tabelelor

Tabelele sunt create prin clasa `SchemaInit`, care contine o metoda statica `createTable`. In `Main`, sunt definite si apelate toate instructiunile SQL necesare pentru crearea tabelelor daca nu exista deja.

## Servicii CRUD

Am creat o interfata generica `CRUDService<T>`, care defineste operatii standard de creare, citire, actualizare si stergere. Pentru urmatoarele clase au fost implementate servicii:

- `CourseProgressServiceImpl` – gestioneaza progresul studentilor intr-un curs (INSERT, SELECT, UPDATE, DELETE din tabela `course_progress`)
- `CourseServiceImpl` – gestioneaza cursurile (INSERT, SELECT, UPDATE, DELETE din tabela `courses`)
- `StudentServiceImpl` – gestioneaza studentii (INSERT, SELECT, UPDATE, DELETE din tabela `users`, doar pentru user_type = 'student')
- `TeacherServiceImpl` – gestioneaza relatia dintre profesori si cursuri, si opereaza pe structurile din memorie

Toate aceste clase sunt singleton si implementeaza `CRUDService<T>`.

## Serviciul de Audit

S-a creat un serviciu `AuditService`, care este un singleton si scrie in fisierul `audit.csv` fiecare actiune efectuata (ex: creare student, actualizare curs). Formatul este:

```
actiune,timestamp
```

Acest serviciu este apelat in toate metodele CRUD relevante.

## Exemplu de functionalitate in `Main`

In clasa `Main` se:

- initializeaza tabelele
- adauga un student si un curs
- extrage ID-urile corespunzatoare
- adauga progres pentru student la curs
- actualizeaza progresul
- sterge progresul, cursul si studentul
- inchide conexiunea la baza de date

```java
Student student = new Student("Ion", "Popescu", "ionpop", "ion@email.com", "parola");
studentService.create(student);
Course course = new Course("Programare Java", "bazele limbajului Java");
courseService.create(course);
Long studentId = studentService.getIdByUsername("ionpop");
Long courseId = courseService.getIdByTitle("Programare Java");
if (studentId != null && courseId != null) {
    CourseProgress progress = new CourseProgress();
    progress.setCompletionPercentage(40);
    progressService.create(progress, studentId, courseId);
    progress.setCompletionPercentage(85);
    progressService.update(progress, studentId, courseId);
    progressService.deleteByStudentAndCourse(studentId, courseId);
    courseService.delete(courseId);
    studentService.delete(studentId);
}
```

## Concluzie

Proiectul este structurat modular, folosind servicii singleton pentru interactiunea cu baza de date si servicii de audit pentru urmarirea actiunilor efectuate. Aceasta arhitectura permite extinderea usoara a sistemului si ofera o separare clara intre logica de afaceri si persistenta.