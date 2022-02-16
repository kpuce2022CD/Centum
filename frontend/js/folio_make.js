var mode='public'; //공개 설정. 디폴트는 public
var make=[]; //제작중인 단
var number=0; //단 개수
var make_f1=[]; //원래 제작해 놓은 포트폴리오 정보(종류), youtube=유투브 영상, video=영상 파일, image=사진 파일, info=설명, git=깃허브 아이디
var make_f2=[]; //원래 제작해 놓은 포트폴리오 정보(src, 텍스트)

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
function video_upf(input){
    var video_upload;
    
    video_upload=document.getElementById("video-ch");
    make[number]=video_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    make[number].firstElementChild.nextElementSibling.firstElementChild.src=input;
    make[number].firstElementChild.nextElementSibling.firstElementChild.nextElementSibling.style.display='none';
    make[number].firstElementChild.nextElementSibling.firstElementChild.style.display='flex';
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
function image_upf(input){
    var image_upload;
    var newImage;
    var up;

    image_upload=document.getElementById("image-ch");
    make[number]=image_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    fo.append(make[number]);

    up=make[number].firstElementChild.nextElementSibling.firstElementChild.firstElementChild;

    newImage = document.createElement("img");
    newImage.setAttribute("class", 'img');
    newImage.setAttribute("id", 'img');

    newImage.src = input;   

    newImage.style.width = "100%";
    newImage.style.height = "100%";
    newImage.style.objectFit = "contain";

    var container = up.nextElementSibling;
    container.appendChild(newImage);
    up.style.visibility = 'hidden';

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
function info_upf(input){
    var info_upload;
    info_upload=document.getElementById("info");
    make[number]=info_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    fo.append(make[number]);
    
    make[number].firstElementChild.nextElementSibling.firstElementChild.value=input;

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
function git_upf(input){
    var git_upload;
    git_upload=document.getElementById("git");
    make[number]=git_upload.cloneNode(true);
    make[number].firstElementChild.value=number;
    make[number].style.display="flex";
    fo.append(make[number]);
    
    make[number].firstElementChild.nextElementSibling.firstElementChild.value=(input);

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
function videoF_upf(input){
    var videoF_upload;
    var me;
    var up;

    videoF_upload=document.getElementById("videoFile");
    make[number]=videoF_upload.cloneNode(true);
    make[number].firstElementChild.value = number;
    make[number].style.display="flex";
    fo.append(make[number]);
    
    me=make[number].firstElementChild.nextElementSibling.firstElementChild;
    up=me.nextElementSibling;
    up.src=input;
    
    me.style.display='none';
    up.style.display='flex';

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

function start(){
    for(var i = 0; i<make_f1.length; i++){
        if (make_f1[number]=='youtube'){
            video_upf(make_f2[number]);
        }
        else if(make_f1[number]=='image'){
            image_upf(make_f2[number]);
        }
        else if(make_f1[number]=='video'){
            videoF_upf(make_f2[number]);
        }
        else if(make_f1[number]=='info'){
            info_upf(make_f2[number]);
        }
        else if(make_f1[number]=='git'){
            git_upf(make_f2[number]);
        }
    }
}

function set_pub(){
    mode='public';
}
function set_pri(){
    mode='private';
}