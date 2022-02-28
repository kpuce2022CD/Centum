let replyBtn = document.querySelectorAll('.reply-btn');
let commentAreaNodes = document.getElementsByClassName('comment-area');

replyBtn.forEach(target => {
    target.addEventListener('click', e => {
        let commentArea = target.parentNode.parentNode.parentNode.parentNode.parentNode;
        for (node of commentAreaNodes) {
            if (node != commentArea) {
                node.classList.remove("open");
            }
        }
        commentArea.classList.toggle("open");
    });
});