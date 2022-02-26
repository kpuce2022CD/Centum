let commentTextarea = document.getElementById('comment-textarea');

commentTextarea.addEventListener('input', e => {
    commentTextarea.style.height = 'auto';
    let height = commentTextarea.scrollHeight;
    commentTextarea.style.height = `${height}px`;
});