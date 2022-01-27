var mode=0;
var make=[];
var number=0;

function loadFile(input) {
    var file = input.files[0];
    var newImage;

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

function loadVideoFile(input) {
    var video_file = input.files[0];
    var test_file=document.getElementsByClassName("videoFi");
    test_file[0].src=URL.createObjectURL(video_file);
    document.getElementById('video-upload').style.display='none';
    document.getElementById('video-file').height=document.getElementById('videoFi').height;
};

function video_in(input){
    hz.src=input;
    hz.style.display="flex";
    hide.style.display="none";
}

function video_up(){
    var video_upload;
    video_upload=document.getElementById("video-ch");
    make[number]=video_upload.cloneNode(true);
    make[number].style.display="flex";
    fo.append(make[number]);
    number++;
}

function image_up(){
    var image_upload;
    image_upload=document.getElementById("image-ch");
    make[number]=image_upload.cloneNode(true);
    make[number].style.display="flex";
    fo.append(make[number]);
    number++;
}

function info_up(){
    var info_upload;
    info_upload=document.getElementById("info");
    make[number]=info_upload.cloneNode(true);
    make[number].style.display="flex";
    fo.append(make[number]);
    number++;
}

function videoF_up(){
    var videoF_upload;
    videoF_upload=document.getElementById("videoFile");
    make[number]=videoF_upload.cloneNode(true);
    make[number].style.display="flex";
    fo.append(make[number]);
    number++;
}

function reset(){

}