window.addEventListener('scroll', () => {
    let scrollLocation = document.documentElement.scrollTop;
    if (scrollLocation < 20) {
        header.style.backgroundColor = 'transparent';
        header.style.border = 'none';
        logo.style.color = '#fff';
        for (let i of navList) {
            i.firstElementChild.style.color = '#fff';
        }
        startButton.style.borderColor = '#fff';
        startButton.style.color = '#fff';
    } else {
        header.style.borderBottom = '1px solid #eee';
        header.style.backgroundColor = '#fff';
        logo.style.color = '#000';
        for (let i of navList) {
            i.firstElementChild.style.color = '#000';
        }
        startButton.style.borderColor = '#000';
        startButton.style.color = '#000';
    }
});