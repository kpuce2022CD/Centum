var mode=[]; //들어가있는 자료의 종류(사진파일, 영상파일, 유튜브 등). 순서대로. youtube=유투브 영상, video=영상 파일, image=사진 파일, info=설명, git=깃허브 아이디
var make=[]; //들어가있는 파일의 src나 텍스트
var make_date='0000.00.00'; //최근 수정 날짜
var setting='public'; //공개 설정
var title='테스트 제목';
var file_where='../../image/portfolio';
var id;

function id_set(input){
    id=input;
}

function add(){
    for(var i=0; i<mode.length; i++){
        if(mode[i]=='youtube'){ //유튜브 영상
            var page=document.createElement('iframe');
            page.src=make[i];
            page.width='1000px';
            page.height='500px';
            page.style.display='flex';
            page.style.marginLeft='auto';
            page.style.marginRight='auto';
            page.style.marginBottom='25px';
            fo.appendChild(page);
        }
        else if(mode[i]=='image'){ //사진 파일
            var page=document.createElement('img');
            var link=file_where+'/'+id+'/'+make[i]
            page.src=link;
            console.log(link);
            page.style.width='1000px';
            page.style.display='flex';
            page.style.marginLeft='auto';
            page.style.marginRight='auto';
            page.style.marginBottom='25px';
            fo.appendChild(page);
        }
        else if(mode[i]=='video'){ //영상 파일
            var page=document.createElement('video');
            page.src=make[i];
            page.width='1000px';
            page.height='500px';
            page.style.display='flex';
            page.style.marginLeft='auto';
            page.style.marginRight='auto';
            page.style.marginBottom='25px';
            fo.appendChild(page);
        }
        else if(mode[i]=='info'){ //설명
            var page=document.createElement('p');
            page.innerText=make[i];
            page.style.width='1000px';
            page.style.display='flex';
            page.style.border="1px solid black";
            page.style.backgroundColor='white';
            page.style.marginLeft='auto';
            page.style.marginRight='auto';
            page.style.marginBottom='25px';
            fo.appendChild(page);
        }
        else if(mode[i]=='git'){ //git 아이디
        }
    }

    var date=document.getElementById('date_2');
    date.innerText=make_date;
    document.getElementById('title').innerText=title;

    if(setting == 'public'){
        var pub=document.getElementById('set-pub');
        var pri=document.getElementById('set-pri');
        pub.style.backgroundColor='black';
        pri.style.backgroundColor='transparent';
    }

    else{
        var pub=document.getElementById('set-pub');
        var pri=document.getElementById('set-pri');
        pri.style.backgroundColor='black';
        pub.style.backgroundColor='transparent';
    }
}

function result() {
    var jsonData=document.getElementById('value_test').value;
    console.log(jsonData);

    var data=JSON.parse(jsonData);
    title=data.title;
    for(var i in data.index){
        mode[i]=data.index[i].what;
        make[i]=data.index[i].src;
    }
    setting=data.view;
    make_date=data.date;
    console.log(data);
    add();
}