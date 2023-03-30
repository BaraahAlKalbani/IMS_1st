const username = "root";
const password = "admin";
const credentials = btoa(`${username}:${password}`);

const teacherIdSelect1 = document.getElementById("teacherId1");
const teacherIdSelect2 = document.getElementById("teacherId2");

const courseIdSelect1 = document.getElementById("id2");
const courseIdSelect2 = document.getElementById("id3");

function addOptionsToSelectTeacher(selectElement) {
  fetch("http://localhost:8080/teachers")
    .then((response) => response.json())
    .then((teachers) => {
      teachers.forEach((teacher) => {
        const option = document.createElement("option");
        option.value = teacher.id;
        option.text = teacher.id + " : " + teacher.name;
        selectElement.appendChild(option);
      });
    })
    .catch((error) => {
      console.error("Error fetching teachers:", error);
    });
}

addOptionsToSelectTeacher(teacherIdSelect1);
addOptionsToSelectTeacher(teacherIdSelect2);

function addOptionsToSelectCourse(selectElement) {
  fetch("http://localhost:8080/courses")
    .then((response) => response.json())
    .then((courses) => {
      courses.forEach((course) => {
        const option = document.createElement("option");
        option.value = course.id;
        option.text = course.id + ": " + course.name;
        selectElement.appendChild(option);
      });
    })
    .catch((error) => {
      console.error("Error fetching courses:", error);
    });
}
addOptionsToSelectCourse(courseIdSelect1);
addOptionsToSelectCourse(courseIdSelect2);

//add
const addCourseForm = document.querySelector("form");
const addCourseButton = document.getElementById("add");

addCourseButton.addEventListener("click", (event) => {
  event.preventDefault();

  const name = document.getElementById("name1").value;
  const desc = document.getElementById("desc1").value;
  const teacherId = document.getElementById("teacherId1").value;

  if (name.trim() === "" || desc.trim() === "" || teacherId === "0") {
    alert("Please fill in all fields!");
  } else {
    const course = {
      name: name,
      description: desc,
      teacherId: teacherId,
    };

    fetch("http://localhost:8080/courses", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(course),
    })
      .then((response) => response.json())
      .then((course) => {
        console.log("Course added:", course);
        addCourseForm.reset();
        alert("Course added successfully.");
      })
      .catch((error) => {
        console.error("Error adding course:", error);
      });
  }
});

//Update
const updateForm = document.getElementById("update-form");
const updateButton = document.getElementById("update");

updateForm.addEventListener("submit", (event) => {
  event.preventDefault();

  const id = document.getElementById("id3").value;
  const name = document.getElementById("name2").value;
  const desc = document.getElementById("desc2").value;
  const teacherId = document.getElementById("teacherId2").value;

  if (!id) {
    alert("Please enter a course ID.");
    return;
  }

  if (!name) {
    alert("Please enter a course name.");
    return;
  }

  if (!teacherId) {
    alert("Please select a teacher ID.");
    return;
  }

  const course = {
    id: id,
    name: name,
    description: desc,
    teacherId: teacherId,
  };

  fetch(`http://localhost:8080/courses/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(course),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to update course.");
      }
      alert("Course updated successfully.");
    })
    .catch((error) => {
      console.error("Error updating course:", error);
      alert("Failed to update course.");
    });
});

//Delete
const deleteBtn = document.getElementById("delete");

deleteBtn.addEventListener("click", (event) => {
  event.preventDefault(); // prevent form submission

  const courseId = document.getElementById("id3").value;

  fetch(`/api/courses/${courseId}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to delete course");
      }
      alert("Course deleted successfully.");
      console.log("Course deleted successfully");
    })
    .catch((error) => {
      console.error("Error deleting course:", error);
    });
});
