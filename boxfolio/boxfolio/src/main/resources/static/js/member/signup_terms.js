let acceptCheckbox = document.getElementById('accept-term');
let nextBtn = document.getElementById('next-btn');

acceptCheckbox.addEventListener('change', e => {
    if (acceptCheckbox.checked == true) {
        nextBtn.disabled = false;
    } else {
        nextBtn.disabled = true;
    }
});