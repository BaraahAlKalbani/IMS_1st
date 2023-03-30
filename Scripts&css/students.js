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

//display table
const studentsTable = document.getElementById("students-table");

fetch("http://localhost:8080/students", {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
    Authorization: "Basic " + btoa(username + ":" + password),
  },
})
  .then((response) => response.json())
  .then((students) => {
    const studentsTable = document.getElementById("students-table");

    students.forEach((student) => {
      const row = document.createElement("tr");
      row.innerHTML = `
      <td>${student.id}</td>
      <td>${student.name}</td>
      <td>${student.email}</td>
    `;
      studentsTable.appendChild(row);
    });
  });
