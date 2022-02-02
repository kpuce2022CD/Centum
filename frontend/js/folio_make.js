var mode=0;
var make=[];
var number=0;

function loadFile(input) {
    var file = input.files[0];
    var newImage;
    var up;
    up=input.parentNode.parentNode;

    newImage = document.createElement("img");
    newImage.setAttribute("class", 'img');
    newImage.setAttribute("id", 'img');

    newImage.src = URL.createObjectURL(file);   

    newImage.style.width = "100%";
    newImage.style.height = "100%";
    newImage.style.objectFit = "contain";

    var container = up.nextElementSibling;
    container.appendChild(newImage);
    up.style.visibility = 'hidden';
};

function loadVideoFile(input) {
    var me;
    var up;
    var file;
    
    me=input.parentNode.parentNode;
    up=me.nextElementSibling;
    file=input.files[0];
    up.src=URL.createObjectURL(file);
    
    me.style.display='none';
    up.style.display='flex';
};

function video_in(input, me){
    var up;
    up=me.previousElementSibling;
    up.src=input;
    up.style.display="flex";
    me.style.display="none";
}

function video_up(){
    var video_upload;
    video_upload=document.getElementById("video-ch");
    make[number]=video_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    fo.append(make[number]);
    make[number].style.order=number;
    
    number++;
}

function image_up(){
    var image_upload;
    image_upload=document.getElementById("image-ch");
    make[number]=image_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    fo.append(make[number]);
    make[number].style.order=number;
    number++;
}

function info_up(){
    var info_upload;
    info_upload=document.getElementById("info");
    make[number]=info_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    fo.append(make[number]);
    make[number].style.order=number;
    number++;
}

function git_up(){
    var git_upload;
    git_upload=document.getElementById("git");
    make[number]=git_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    fo.append(make[number]);
    make[number].style.order=number;
    number++;
}

function videoF_up(){
    var videoF_upload;
    videoF_upload=document.getElementById("videoFile");
    make[number]=videoF_upload.cloneNode(true);
    make[number].firstElementChild.value = number;
    make[number].style.display="flex";
    fo.append(make[number]);
    make[number].style.order=number;
    number++;
}

function reset(){
    for (var i = 0; i < make.length; i++) {
        make[i].firstElementChild.value = i;
        make[i].style.order=i;
    }
}

function change(me, input){
    var now;
    var now_num;
    var cha_num;
    now=me.parentNode;
    now_num=now.style.order;
    cha_num=input;
    now=make[now_num];
    make.splice(now_num,1);
    make.splice(cha_num, 0, now);
    reset();
}

function dele(input){
    var me;
    var me_num;
    me=input.parentNode;
    me_num=me.style.order;
    make[me_num].remove();
    make.splice(me_num,1);
    number--;
    reset();
}