var newImage;
var mode=0;

function loadFile(input) {
    var file = input.files[0];

    newImage = document.createElement("img");
    newImage.setAttribute("class", 'img');
    newImage.setAttribute("id", 'img');

    newImage.src = URL.createObjectURL(file);   

    newImage.style.width = "100%";
    newImage.style.height = "100%";
    newImage.style.objectFit = "contain";

    var container = document.getElementById('image-show');
    container.appendChild(newImage);
    document.getElementById('image-upload').style.visibility = 'hidden';
};

function video_in(input){
    hz.src=input;
    hz.style.display="flex";
    hide.style.display="none";
}

function video_up(){
    document.getElementById('video-ch').style.display="flex";
    document.getElementById('image-ch').style.display="none";
    document.getElementById('rec').style.display="none";
}
function image_up(){
    document.getElementById('video-ch').style.display="none";
    document.getElementById('image-ch').style.display="flex";
    document.getElementById('rec').style.display="none";
}
function image_re(){
    document.getElementById('image-upload').style.visibility = "visible";
    newImage.src=null;
    newImage.style.visibility="hidden";
}
function video_re(){
    hz.src=null;
    document.getElementById('hide').value="유튜브 src를 입력하세요";
    hz.style.display="none";
    hide.style.display="flex";
}
function reset(){
    document.getElementById('video-ch').style.display="none";
    document.getElementById('image-ch').style.display="none";
    document.getElementById('rec').style.display="flex";
    if(hz.src != null){
        video_re();
    }
    if(newImage.src != null){
        image_re();
    }
}