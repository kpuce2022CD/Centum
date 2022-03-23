var best_link=document.getElementsByClassName('b_href');
var best_id=document.getElementsByClassName('b_id');
var best_title=document.getElementsByClassName('b_title');
var best_info=document.getElementsByClassName('b_info');
var best_date=document.getElementsByClassName('b_date');
var best_star=document.getElementsByClassName('b_star');
var best_scrap=document.getElementsByClassName('b_star');

var normal_link=document.getElementsByClassName('n_href');
var normal_id=document.getElementsByClassName('n_id');
var normal_title=document.getElementsByClassName('n_title');
var normal_info=document.getElementsByClassName('n_info');
var normal_date=document.getElementsByClassName('n_date');
var normal_star=document.getElementsByClassName('n_star');
var normal_scrap=document.getElementsByClassName('n_star');

var link_b=['#'], id_b=['#'], title_b=['#'], info_b=['#'], date_b=['#'], star_b=['#'], scrap_b=['#'];
var link_n=['#'], id_n=['#'], title_n=['#'], info_n=['#'], date_n=['#'], star_n=['#'], scrap_n=['#'];

function set_best(num){
    best_link[num].href=link_b[num];
    best_id[num].innerText=id_b[num]+'님의 포트폴리오 입니다.';
    best_title[num].innerText=title_b[num];
    best_info[num].innerText=info_b[num];
    best_date[num].innerText=date_b[num];
    best_star[num].innerText=star_b[num];
    best_scrap[num].innerText=scrap_b[num];
}
function set_normal(num){
    normal_link[num].href=link_n[num];
    normal_id[num].innerText=id_n[num]+'님의 포트폴리오 입니다.';
    normal_title[num].innerText=title_n[num];
    normal_info[num].innerText=info_n[num];
    normal_date[num].innerText=date_n[num];
    normal_star[num].innerText=star_n[num];
    normal_scrap[num].innerText=scrap_n[num];
}

function start(b_num, n_num){
    for(var i in b_num){
        set_best(i);
    }
    for(var i in n_num){
        set_normal(i);
    }
}

function setting_b(num, cont, star, id){
    var data=JSON.parse(cont);
    console.log(data);
    title_b[num]=data.title;
    id_b[num]=id;
    star_b[num]=star;
    date_b[num]=data.date;
    scrap_b[num]=0;
    info_b[num]='웹/백엔드';
    set_best(0);
}

function next_folio(){ //포트폴리오 리스트 다음 리스트로 변경
    for(var i in 4){
        set_normal(i);
    }
}
function back_folio(){ //포트폴리오 리스트 이전 리스트로 변경
    for(var i in 4){
        set_normal(i);
    }
}