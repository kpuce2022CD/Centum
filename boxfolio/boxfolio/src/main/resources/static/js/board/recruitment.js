const editor = document.getElementById('editor');
const btnBold = document.getElementById('tool-bold');
const btnItalic = document.getElementById('tool-italic');
const btnUnderline = document.getElementById('tool-under');
const btnStrike = document.getElementById('tool-cancel');
const btnOrderedList = document.getElementById('tool-orderlist');
const btnUnorderedList = document.getElementById('tool-unorderlist');
const btnImage = document.getElementById('tool-image');
const btnFull = document.getElementById('tool-full');
const btnLeft = document.getElementById('tool-left');
const btnCenter = document.getElementById('tool-center');
const btnRight = document.getElementById('tool-right');
const btnUndo = document.getElementById('tool-undo');
const btnRedo = document.getElementById('tool-redo');
const fontSizeSelector = document.getElementById('tool-fontsize');
const fontNameSelector = document.getElementById('tool-font');

const fontSizeList = [10, 13, 16, 18, 24, 32, 48];

btnBold.addEventListener('click', function() {
    setStyle('bold');
});

btnItalic.addEventListener('click', function() {
    setStyle('italic');
});

btnUnderline.addEventListener('click', function() {
    setStyle('underline');
});

btnStrike.addEventListener('click', function() {
    setStyle('strikeThrough');
});

btnOrderedList.addEventListener('click', function() {
    setStyle('insertOrderedList');
});

btnUnorderedList.addEventListener('click', function() {
    setStyle('insertUnorderedList');
});

btnUndo.addEventListener('click', function() {
    setStyle('undo');
})

btnRedo.addEventListener('click', function() {
    setStyle('redo');
});

btnFull.addEventListener('click', function() {
    setStyle('justifyFull')
})

btnLeft.addEventListener('click', function() {
    setStyle('justifyLeft')
})

btnCenter.addEventListener('click', function() {
    setStyle('justifyCenter')
})

btnRight.addEventListener('click', function() {
    setStyle('justifyRight')
})

btnImage.addEventListener('click', function() {
    
});

fontSizeSelector.addEventListener('change', function() {
    changeFontSize(this.value);
});

fontNameSelector.addEventListener('change', function() {
    changeFontName(this.value);
});

editor.addEventListener('keydown', function() {
    checkStyle();
});

editor.addEventListener('mousedown', function() {
    checkStyle();
});

function loadFile(input) {
    let file = input.files[0];

    let newImage = document.createElement("img");
    newImage.setAttribute("class", 'image');

    newImage.src = URL.createObjectURL(file);

    newImage.style.width = "auto";
    newImage.style.height = "100%";
    newImage.style.objectFit = "contain";

    let container = document.getElementById('editor');
    container.appendChild(newImage);
    focusEditor();
}

function changeFontName(name) {
    document.execCommand('fontName', false, name);
    focusEditor();
}

function changeFontSize(size) {
    document.execCommand('fontSize', false, size);
    focusEditor();
}

function getComputedStyleProperty(el, propName) {
    if (window.getComputedStyle) {
        return window.getComputedStyle(el, null)[propName];
    } else if (el.currentStyle) {
        return el.currentStyle[propName];
    }
}

function reportFont() {
    let containerEl, sel;
    if (window.getSelection) {
        sel = window.getSelection();
        if (sel.rangeCount) {
            containerEl = sel.getRangeAt(0).commonAncestorContainer;
            if (containerEl.nodeType === 3) {
                containerEl = containerEl.parentNode;
            }
        }
        else if ((sel = document.selection) && sel.type !== 'Control') {
            containerEl = sel.createRange().parentElement();
        }

        if (containerEl) {
            const fontSize = getComputedStyleProperty(containerEl, 'fontSize');
            const fontName = getComputedStyleProperty(containerEl, 'fontFamily');
            const size = parseInt(fontSize.replace('px', ''));
            fontSizeSelector.value = fontSizeList.indexOf(size) + 1;
            fontNameSelector.value = fontName.replaceAll('"', '')
        }
    }
}

function focusEditor() {
    editor.focus({preventScroll: true});
}

function setStyle(style) {
    document.execCommand(style);
    focusEditor();
    checkStyle();
}

function checkStyle() {
    if (isStyle('bold')) {
        btnBold.classList.add('active');
    } else {
        btnBold.classList.remove('active');
    }
    if (isStyle('italic')) {
        btnItalic.classList.add('active');
    } else {
        btnItalic.classList.remove('active');
    }
    if (isStyle('underline')) {
        btnUnderline.classList.add('active');
    } else {
        btnUnderline.classList.remove('active');
    }
    if (isStyle('strikeThrough')) {
        btnStrike.classList.add('active');
    } else {
        btnStrike.classList.remove('active');
    }
    if (isStyle('insertOrderedList')) {
        btnOrderedList.classList.add('active');
    } else {
        btnOrderedList.classList.remove('active');
    }
    if (isStyle('insertUnorderedList')) {
        btnUnorderedList.classList.add('active');
    } else {
        btnUnorderedList.classList.remove('active');
    }
    if (isStyle('justifyFull')) {
        btnFull.classList.add('active');
    } else {
        btnFull.classList.remove('active');
    }
    if (isStyle('justifyLeft')) {
        btnLeft.classList.add('active');
    } else {
        btnLeft.classList.remove('active');
    }
    if (isStyle('justifyCenter')) {
        btnCenter.classList.add('active');
    } else {
        btnCenter.classList.remove('active');
    }
    if (isStyle('justifyRight')) {
        btnRight.classList.add('active');
    } else {
        btnRight.classList.remove('active');
    }
    reportFont();
}

function isStyle(style) {
    return document.queryCommandState(style);
}

const saveEdit = document.getElementById('save-edit');

saveEdit.addEventListener('click', function() {
    const urlToSendForBoard = "http://localhost:8080/wonho_free/EditServlet?cmd=uploadBoard";

    const title = document.getElementById('edit-title');
    var content = document.getElementById('editor');
    const titleText = title.value;
    const contentText = content.innerHTML;

    const params = {'title':titleText, 'content':contentText};
    sendPost(urlToSendForBoard, params);
});

// 데이터 post 전송
function sendPost(url, params) {
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', url);
    document.characterSet = "utf-8";
    
    for (var key in params) {
        var hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', key);
        hiddenField.setAttribute('value', params[key]);
        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();
}