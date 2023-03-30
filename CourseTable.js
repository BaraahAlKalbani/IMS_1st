const coursesTable = document.getElementById("courses-table");

fetch("http://localhost:8080/courses")
  .then((response) => response.json())
  .then((courses) => {
    courses.forEach((course) => {
      const row = coursesTable.insertRow(-1);
      row.insertCell().textContent = course.id;
      row.insertCell().textContent = course.name;
      row.insertCell().textContent = course.description;
      row.insertCell().textContent = course.teacherId;
    });
  })
  .catch((error) => {
    console.error("Error fetching courses:", error);
  });
