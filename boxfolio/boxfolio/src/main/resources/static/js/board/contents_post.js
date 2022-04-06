const saveEdit = document.getElementById('save-edit');
const boardForm = document.getElementById('board-form');

// 데이터 post 전송
function sendPost(params) {
    for (let key in params) {
        let hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', key);
        hiddenField.setAttribute('value', params[key]);
        boardForm.appendChild(hiddenField);
    }
    boardForm.submit();
}

saveEdit.addEventListener('click', function() {
    const contents = document.getElementById('editor');
    let contentValue = contents.innerHTML;

    const params = {'contents':contentValue};
    sendPost(params);
});