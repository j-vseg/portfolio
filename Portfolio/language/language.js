const body = document.getElementById('body');
const dfAlert = document.getElementById('df-alert');
const alertHeader = document.getElementById('alert-header');
const alertBtnLangEn = document.getElementById('alertBtnLangEn');
const alertBtnLangNl = document.getElementById('alertBtnLangNl');

const langCookie = getCookie('preferedLanguage');
const btnLang = document.getElementById('btnLang');

body.style.overflowY = 'hidden';
dfAlert.style.display = 'flex';

console.log(langCookie);
if (langCookie != null) {
    if (langCookie == "nl") {
        disableAlert('nl');
    }
    else {
        disableAlert('en');
    }
}

alertBtnLangNl.addEventListener('click', function() {
    disableAlert('nl');
});
alertBtnLangEn.addEventListener('click', function() {
    disableAlert('en');
});
alertBtnLangNl.addEventListener("mouseover", function() {
    alertHeader.innerHTML = "Kies je voorkeurstaal:";
});
alertBtnLangEn.addEventListener("mouseover", function() {
    alertHeader.innerHTML = "Select your prefered langauge:";
});

function selectedLanguage(language) {
    if (language == "nl") {
        fetch("language/lang.nl.json")
        .then((response) => response.json())
        .then((translations) => {
            updateUI(translations);
    
            btnLang.innerHTML = "";
            const img = document.createElement('img');
            img.src = 'assets/icons/netherlands.png';
            btnLang.appendChild(img);

            const d = new Date();
            var year = d.getFullYear();
            var month = d.getMonth();
            var day = d.getDate();
            const nextYear = new Date(year + 1, month, day);
            document.cookie = `preferedLanguage=nl; expires=${nextYear.toUTCString()}`
        })
        .catch((error) => console.log(error));
    }
    else {
        fetch("language/lang.en.json")
        .then((response) => response.json())
        .then((translations) => {
            updateUI(translations);
    
            btnLang.innerHTML = "";
            const img = document.createElement('img');
            img.src = 'assets/icons/united-states.png';
            btnLang.appendChild(img);

            const d = new Date();
            var year = d.getFullYear();
            var month = d.getMonth();
            var day = d.getDate();
            const nextYear = new Date(year + 1, month, day);
            document.cookie = `preferedLanguage=en; expires=${nextYear.toUTCString()}`

        })
        .catch((error) => console.log(error));
    }
}

function updateUI(translations) {
    const allLangEl = Array.from(document.querySelectorAll('*[id]')).filter((id) => id.id.includes('lang_'));

    allLangEl.forEach((el) => {
        const translation = el.id.match(/lang_[A-z0-9]*/)[0].substring(5, 75);

        if (translation == "stay_signify") {
            el.innerHTML = `${translations["sep"]} 2023 - ${translations["jan"]} 2024`
        }
        else if (translation == "stay_renew") {
            el.innerHTML = `${translations["may"]} 2021 - ${translations["sep"]} 2021`
        }
        else {
            el.innerHTML = translations[translation];
        }
    });
}

function disableAlert(langauge) {
    if (langauge == "nl") {
        dfAlert.style.display = 'none';
        body.style.overflowY = 'auto';
        selectedLanguage('nl');
    }
    else {
        dfAlert.style.display = 'none';
        body.style.overflowY = 'auto';
        selectedLanguage('en');
    }
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return null;
}