const studentsTable = document.getElementById("students-table");

fetch("http://localhost:8080/students")
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
