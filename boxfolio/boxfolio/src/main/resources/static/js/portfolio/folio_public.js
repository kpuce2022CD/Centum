var best_maker=['제작자1','제작자2','제작자3','제작자4']; //최다 추천작 제작자 이름(4개까지)
var best_count=['100','200','300','400']; //최다 추천작들 추천수
var best_url=['folio_other.html','folio_other.html','folio_other.html','folio_other.html']; //최다 추천작 링크
var best_list=[];

var normal_maker=['제작자5','제작자6','제작자7','제작자8']; //일반 작품 제작자 이름(4개까지)
var normal_count=['10','20','30','40']; //일반 작품 추천수
var normal_url=['#','#','#','#']; //일반 작품 링크
var normal_list=[];


function set_best(){//표시는 일단 제작자 이름이랑 추천 수만 됩니다
    var copy; //나중에 썸네일 이미지 같은거 데이터로 넣게 되면 띄워줄수도 있겠지만 일단은 이렇게만 해뒀어요
    var loc;
    loc=document.getElementById('best_index');
    copy=document.getElementById('index');
    for(var i=0; i<best_maker.length; i++){    
        best_list[i]=copy.cloneNode(true);
        best_list[i].firstElementChild.innerText="제작자 : " + best_maker[i];
        best_list[i].firstElementChild.nextElementSibling.innerText="추천 수 : " + best_count[i];
        best_list[i].href=best_url[i];
        best_list[i].style.display='flex';
        loc.append(best_list[i]);
    }
}
function set_normal(){
    var copy;
    var loc;
    loc=document.getElementById('normal_index');
    copy=document.getElementById('index');
    for(var i=0; i<normal_maker.length; i++){    
        normal_list[i]=copy.cloneNode(true);
        normal_list[i].firstElementChild.innerText="제작자 : " + normal_maker[i];
        normal_list[i].firstElementChild.nextElementSibling.innerText="추천 수 : " + normal_count[i];
        normal_list[i].href=normal_url[i];
        normal_list[i].style.display='flex';
        loc.append(normal_list[i]);
    }
}

function start(){
    set_best();
    set_normal();
}

function next_folio(){ //포트폴리오 리스트 다음 리스트로 변경
    for(var i=0; i<normal_list.length; i++){//이전에 있던 리스트 삭제
        normal_list[i].remove();
    }
    set_normal();//새로 입력된 데이터로 재 표시
}
function back_folio(){ //포트폴리오 리스트 이전 리스트로 변경
    for(var i=0; i<normal_list.length; i++){
        normal_list[i].remove();
    }
    set_normal();
}