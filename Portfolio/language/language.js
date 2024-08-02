const switchLang = document.getElementById("switch-language");

console.log(switchLang);

if (switchLang.value == "nl") {
    const works = document.querySelectorAll('#lang_works');
    works.forEach((el) => {
        el.innerHTML = lang.works;
    })
}
else { 
}