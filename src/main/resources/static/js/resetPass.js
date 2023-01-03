pass1 = document.getElementById("newPass");
pass2 = document.getElementById("newPass2");
btn = document.getElementById("smt");

console.log(pass1)
console.log(pass2)
console.log(btn)

pass1.addEventListener('keyup', (e) =>{
    if (pass1.value != pass2.value){
        btn.disabled = true;
    }else{
        btn.disabled = false;
    }
});

pass2.addEventListener('keyup', (e) =>{
    if (pass1.value != pass2.value){
        btn.disabled = true;
    }else{
        btn.disabled = false;
    }
});


