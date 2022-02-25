let body = document.getElementsByTagName('body')[0];
let nav = document.getElementsByClassName('nav')[0];
let logo = document.getElementById('logo');
let navList = document.getElementsByClassName('nav')[0].children;
let startButton = document.getElementsByClassName('start-btn')[0];

nav.addEventListener('mouseenter', ev => {
    body.classList.add("openmenu");
    logo.style.color = '#000';
        for (let i of navList) {
            i.firstElementChild.style.color = '#000';
        }
        startButton.style.borderColor = '#000';
        startButton.style.color = '#000';
});

nav.addEventListener('mouseleave', ev => {
    let scrollLocation = document.documentElement.scrollTop;
    body.classList.remove("openmenu");
    if (scrollLocation < 20) {
        header.style.backgroundColor = 'transparent';
        logo.style.color = '#fff';
        for (let i of navList) {
            i.firstElementChild.style.color = '#fff';
        }
        startButton.style.borderColor = '#fff';
        startButton.style.color = '#fff';
    } else {
        header.style.backgroundColor = '#fff';
        logo.style.color = '#000';
        for (let i of navList) {
            i.firstElementChild.style.color = '#000';
        }
        startButton.style.borderColor = '#000';
        startButton.style.color = '#000';
    }
});
