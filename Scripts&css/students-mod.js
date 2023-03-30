//Login and logout authentication
const username = localStorage.getItem("username");
const password = localStorage.getItem("password");

if (username == null || password == null) {
  window.location.href = "login.html";
}

const logoffButton = document.querySelector("#logOut");

logoffButton.addEventListener("click", () => {
  localStorage.clear();
  window.location.href = "login.html";
});

//get student Ids to display them
const studentIdSelect1 = document.getElementById("id2");
const studentIdSelect2 = document.getElementById("id3");
function addOptionsToSelectstudent(selectElement) {
  fetch("http://localhost:8080/students", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa(username + ":" + password),
    },
  })
    .then((response) => response.json())
    .then((students) => {
      students.forEach((student) => {
        const option = document.createElement("option");
        option.value = student.id;
        option.text = student.id + ": " + student.name;
        selectElement.appendChild(option);
      });
    })
    .catch((error) => {
      console.error("Error fetching students:", error);
    });
}
addOptionsToSelectstudent(studentIdSelect1);
addOptionsToSelectstudent(studentIdSelect2);

//add new
const addBtn = document.getElementById("add");

addBtn.addEventListener("click", (event) => {
  event.preventDefault(); // prevent form submission

  const name = document.getElementById("name1").value;
  const email = document.getElementById("email1").value;

  const student = {
    name: name,
    email: email,
  };

  if (name === "" || email === "") {
    alert("Please enter name and email.");
    return;
  }

  fetch("http://localhost:8080/students", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa(username + ":" + password),
    },
    body: JSON.stringify(student),
  })
    .then((response) => {
      if (response.ok) {
        alert("successfully added!!");
        console.log("Student added successfully");
      } else {
        console.error("Error adding student");
      }
    })
    .catch((error) => {
      console.error(error);
    });
});

//Update Student

const updateForm = document.forms.updateStudentForm;

updateForm.addEventListener("submit", (event) => {
  event.preventDefault();

  const id = updateForm.elements.id2.value;
  const name = updateForm.elements.name2.value;
  const email = updateForm.elements.email2.value;

  const data = {
    name: name,
    email: email,
  };

  fetch(`http://localhost:8080/students/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa(username + ":" + password),
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (response.ok) {
        alert("Student updated successfully.");
      } else {
        alert("Failed to update student.");
      }
    })
    .catch((error) => {
      console.error(error);
      alert("Failed to update student.");
    });
});

//Delete student
const deleteForm = document.forms.deleteStudentForm;

deleteForm.addEventListener("submit", (event) => {
  event.preventDefault(); // prevent form submission

  const id = deleteForm.elements.id3.value;

  fetch(`http://localhost:8080/students/${id}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa(username + ":" + password),
    },
  })
    .then((response) => {
      if (response.ok) {
        alert("Student deleted successfully.");
        console.log("Student deleted successfully");
      } else {
        console.error("Error deleting student");
      }
    })
    .catch((error) => {
      console.error(error);
    });
});
