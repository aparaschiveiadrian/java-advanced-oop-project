CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(100)        NOT NULL,
    last_name  VARCHAR(100)        NOT NULL,
    username   VARCHAR(100) UNIQUE NOT NULL,
    email      VARCHAR(100)        NOT NULL,
    password   VARCHAR(100)        NOT NULL,
    user_type  VARCHAR(20)         NOT NULL
);

CREATE TABLE IF NOT EXISTS courses
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS quizzes
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(100) NOT NULL,
    course_id INT REFERENCES courses (id)
);

CREATE TABLE IF NOT EXISTS quiz_questions
(
    id       SERIAL PRIMARY KEY,
    quiz_id  INT REFERENCES quizzes (id),
    question TEXT NOT NULL,
    answer   TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS enrollments
(
    id         SERIAL PRIMARY KEY,
    student_id INT REFERENCES users (id),
    course_id  INT REFERENCES courses (id),
    UNIQUE (student_id, course_id)
);

CREATE TABLE IF NOT EXISTS course_progress
(
    id                    SERIAL PRIMARY KEY,
    student_id            INT REFERENCES users (id),
    course_id             INT REFERENCES courses (id),
    completion_percentage INT CHECK (completion_percentage >= 0 AND completion_percentage <= 100),
    UNIQUE (student_id, course_id)
);

CREATE TABLE IF NOT EXISTS teacher_courses
(
    id         SERIAL PRIMARY KEY,
    teacher_id INT REFERENCES users (id),
    course_id  INT REFERENCES courses (id),
    UNIQUE (teacher_id, course_id)
);

CREATE TABLE IF NOT EXISTS classrooms
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) UNIQUE NOT NULL,
    teacher_id INT REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS classroom_students
(
    id           SERIAL PRIMARY KEY,
    classroom_id INT REFERENCES classrooms (id),
    student_id   INT REFERENCES users (id),
    UNIQUE (classroom_id, student_id)
);
