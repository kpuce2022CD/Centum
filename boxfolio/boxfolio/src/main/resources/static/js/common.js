let profileBtn = document.getElementById("profile-btn");
let memberAccess = document.getElementById("member-access");

profileBtn.addEventListener('click', ev => {
   if (memberAccess.className === "") {
       memberAccess.className = "ac-closed";
   } else {
       memberAccess.className = "";
   }
});