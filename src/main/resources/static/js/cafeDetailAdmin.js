// cafeDetailAdmin 파일들에서 공통적으로 사용할 js 파일

// 현재 edit는 사용하지 않음
function edit() {
    let article = document.getElementById("article").innerHTML;
    console.log(article);
    location.href="cafeUpdateDemo.html";
}

function del() {
    if (!confirm("삭제하시겠습니까?")) {
        alert("취소되었습니다");
    } else {
        alert("삭제 기능은 아직 지원하지 않습니다");
    }
}

// 새 글 등록
function apply() {
    alert("등록 기능은 아직 지원하지 않습니다")
    location.href="cafesAdmin.html";
}