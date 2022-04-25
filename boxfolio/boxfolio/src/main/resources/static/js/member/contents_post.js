const modifyBtn = document.getElementById('modify-btn');
const projectForm = document.getElementById('project-form');

modifyBtn.addEventListener('click', function() {
    projectForm.submit();
});