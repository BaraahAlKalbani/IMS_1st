fetch("http://localhost:8080/teachers")
  .then((response) => response.json())
  .then((teachers) => {
    const tableBody = document.getElementById("teachers-table");
    teachers.forEach((teacher) => {
      const row = tableBody.insertRow();
      row.insertCell().textContent = teacher.id;
      row.insertCell().textContent = teacher.name;
      row.insertCell().textContent = teacher.salary;
      row.insertCell().textContent = teacher.email;
    });
  })
  .catch((error) => console.error("Error fetching teachers:", error));
