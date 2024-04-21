const button = document.querySelector("button"),
    toast = document.querySelector(".toast");
(closeIcon = document.querySelector(".close")),
    (progress = document.querySelector(".progress"));

let timer1, timer2;

button.addEventListener("click", () => {
    toast.classList.add("active");
    progress.classList.add("active");

    timer1 = setTimeout(() => {
        toast.classList.remove("active");
    }, 5000);

    timer2 = setTimeout(() => {
        progress.classList.remove("active");
    }, 5300);
});

closeIcon.addEventListener("click", () => {
    toast.classList.remove("active");

    setTimeout(() => {
        progress.classList.remove("active");
    }, 300);

    clearTimeout(timer1);
    clearTimeout(timer2);
});

function showToast() {
    var toast = document.querySelector('.toast');
    toast.style.display = 'block';
    setTimeout(function () {
        toast.style.display = 'none';
    }, 5000);
}

window.addEventListener('scroll', function () {
    var toast = document.querySelector('.toast');
    toast.style.bottom = '0';
});

