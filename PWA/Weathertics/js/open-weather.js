const appKey = "ce5c3cae538be6aeed3b717f99cdefe8";

let citySearchButton;
let citySearchInput;
let cityName;
let citySearchTemperature;
let citySearchUvIndex;
let citySearchWeather;

let divWeatherCountry;
let divSunInformation;
let divUVIndex;
let divWeatherPrediction;
let divNoCity;

// Weather Today
if (document.getElementById("city-search-btn") !== null) {
  citySearchButton = document.getElementById("city-search-btn");
  citySearchInput = document.getElementById("city-search-input");
  cityName = citySearchInput.value;
  citySearchTemperature = document.getElementById("city-search-temp");
  citySearchUvIndex = document.getElementById("city-search-uv-index")
  citySearchWeather = document.getElementById("city-search-weather");

  divWeatherCountry = document.getElementById("weather-country");
  divSunInformation = document.getElementById('sun-information');
  divUVIndex = document.getElementById('uv-index');
  divWeatherPrediction = document.getElementById('weather-prediction');
  divNoCity = document.getElementById('no-city');
}


// Main weather
let weatherMaxTemp = document.getElementById('weather-max-temp');
let weatherMinTemp = document.getElementById('weather-min-temp');
let weatherMain = document.getElementById('weather-weather');
let weatherPressure = document.getElementById('weather-pressure');
let weatherVisiblity = document.getElementById('weather-visiblity');
let weatherHumidity = document.getElementById('weather-humidity');
let weatherIcon = document.getElementById('weather-icon')

// Air Pollution
let windDegrees = document.getElementById('wind-degrees');
let qualitativeName = document.getElementById('qualitative-name');
let windDirection = document.getElementById('wind-direction');

let lat = 0.0;
let long = 0.0;

if (document.getElementById("city-search-btn") !== null) {
  citySearchButton.addEventListener("click", findWeatherDetails);
  citySearchInput.addEventListener("keyup", enterPressed);
  }

function enterPressed(event) {
  if (event.key === "Enter") {
    findWeatherDetails();
  }
}

function findWeatherDetailsMain() {
    getLocation();
    // Search link with location 
    let searchLink = "https://api.openweathermap.org/data/2.5/weather?lat="+ lat +"&lon="+ long + "&appid="+appKey;
    //console.log(searchLink);
    httpRequestAsync(searchLink, theResponse);

    searchLink = "http://api.openweathermap.org/data/2.5/air_pollution?lat="+ lat +"&lon="+ long +"&appid="+appKey;
    //console.log(searchLink);
    httpRequestAsync(searchLink, theAirQualityResponse);


}

function findWeatherDetails() {
  if (citySearchInput.value === "") {
  
  }else {
    divWeatherCountry.style.display = 'inline-flex';
    divSunInformation.style.display = 'block';
    divUVIndex.style.display = 'inline-flex';
    divWeatherPrediction.style.display = 'block';
    divNoCity.style.display = 'none';

    let searchLink = "https://api.openweathermap.org/data/2.5/weather?q=" + citySearchInput.value + "&appid="+appKey;
    //console.log(searchLink);
    httpRequestAsync(searchLink, theResponse);

    getLocation();
    searchLink = "https://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+long+"&appid="+appKey;
    //console.log(searchLink);
    httpRequestAsync(searchLink, thePredictionResponse);
  }
 }

function theResponse(response) {
  let jsonObject = JSON.parse(response);
  //console.log(response);

  // Today
  cityName.innerHTML = jsonObject.name;
  citySearchTemperature.innerHTML = parseInt(jsonObject.main.temp - 273) + "°";
  citySearchWeather.innerHTML = jsonObject.weather[0].main;

  if (document.getElementById("city-search-btn") !== null) {
    weatherIcon.src =  './images/weather-icons/'+ jsonObject.weather[0].main + '.png';
  }
  weatherIcon.alt = jsonObject.weather[0].main;
  weatherMaxTemp.innerHTML = parseInt(jsonObject.main.temp_max - 273) + "° C";
  weatherMinTemp.innerHTML = parseInt(jsonObject.main.temp_min - 273) + "° C";
  weatherMain.innerHTML = jsonObject.weather[0].main;
  weatherPressure.innerHTML = jsonObject.main.pressure + " mb";
  weatherVisiblity.innerHTML = parseInt(jsonObject.visibility / 1000) + " km";
  weatherHumidity.innerHTML = jsonObject.main.humidity + "%";

  if (jsonObject.wind.deg <= 90) {
    windDirection.innerHTML = "North Wind";
  }
  else if (jsonObject.wind.deg <= 180) {
    windDirection.innerHTML = "East Wind";
  }
  else if (jsonObject.wind.deg <= 270) {
    windDirection.innerHTML = "South Wind";
  }
  else {
    windDirection.innerHTML = "West Wind";
  }
}

function theAirQualityResponse(response) {
  let jsonObject = JSON.parse(response);
  //console.log(response);
  qualitativeName.style.width = ((jsonObject.list[0].components.pm2_5 * 100) / 80 ) + "%";
  windDegrees.innerHTML = jsonObject.list[0].components.pm2_5;
  /*if (parseInt(jsonObject.list[0].main.aqi) == 1) { windDegrees.innerHTML = "Good"; }
  else if (parseInt(jsonObject.list[0].main.aqi) == 2) { windDegrees.innerHTML = "Fair"; }
  else if (parseInt(jsonObject.list[0].main.aqi) == 3) { windDegrees.innerHTML = "Moderate"; }
  else if (parseInt(jsonObject.list[0].main.aqi) == 4) { windDegrees.innerHTML = "Poor"; }
  else { windDegrees.innerHTML = "Very Poor"; }*/
}

function httpRequestAsync(url, callback)
{
  //console.log("httpRequestAsync");
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = () => { 
        if (httpRequest.readyState == 4 && httpRequest.status == 200)
            callback(httpRequest.responseText);
    }
    httpRequest.open("GET", url, true); // true for asynchronous 
    httpRequest.send();
}

function getLocation() {
  if (navigator.geolocation) {
    //console.log('getting user location!')
    navigator.geolocation.getCurrentPosition(showPosition);
    
  } else { 
    console.log('Geolocation is not supported by this browser');
  }
}
function showPosition(position) {
  let locationString = "Lat: " + position.coords.latitude + " Long: " + position.coords.longitude;
  console.log(locationString);
  lat =  parseFloat(position.coords.latitude);
  long = parseFloat(position.coords.longitude);
  localStorage.setItem("locationLat", lat);
  localStorage.setItem("locationLong", long);
  getInitWeather(lat,long);
}
function getInitWeather(lat,long){
  let requestlink = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + long + "&appid=" + appKey;
  httpRequestAsync(requestlink, theResponse);
  
}

// Weather prediction
let searchCityNextDayIcon;
let searchCityNextDayDate;
let searchCityNextDayWeather;
let searchCityNextDayMMinMaxTemp;

let searchCity2ndDayIcon;
let searchCity2ndDayDate;
let searchCity2ndDayWeather;
let searchCity2ndDayMMinMaxTemp;

let chartData = [];

if (document.getElementById("city-search-btn") !== null) {
  searchCityNextDayIcon = document.getElementById('search-city-next-day-icon');
  searchCityNextDayDate = document.getElementById('search-city-next-day-date');
  searchCityNextDayWeather = document.getElementById('search-city-next-day-weather');
  searchCityNextDayMMinMaxTemp = document.getElementById('search-city-next-day-min-max-temp');

  searchCity2ndDayIcon = document.getElementById('search-city-2nd-day-icon');
  searchCity2ndDayDate = document.getElementById('search-city-2nd-day-date');
  searchCity2ndDayWeather = document.getElementById('search-city-2nd-day-weather');
  searchCity2ndDayMMinMaxTemp = document.getElementById('search-city-2nd-day-min-max-temp');
}
  
  function thePredictionResponse(response) {
    let jsonObject = JSON.parse(response);
    cityName.innerHTML = jsonObject.name;
    //console.log(response);
    let date = new Date();
    date.setDate(date.getDate() + 1);
    const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    let index = -1;
    let lowTemps = [];
    let highTemps = [];
    for (let idx = 0; idx < 2; idx++) {
      chartData = [];
        for (let i = 0; i < jsonObject.list.length; i++)
        {
          let dayStr = jsonObject.list[i].dt_txt.substring(8, 10);
          if (date.getDate() < 10) { dayStr = jsonObject.list[i].dt_txt.substring(9, 10); }
            let today = new Date();
            let hourStr = jsonObject.list[i].dt_txt.substring(10, 16);

            if (String(dayStr) === String(today.getDate())) { 
              chartData.push({day: hourStr, temp: parseInt(jsonObject.list[i].main.temp - 273)});
            }
            //console.log(String(dayStr));
            //console.log(String(date.getDate()));
            if (String(dayStr) === String(date.getDate())) { 
                index = i; 
                lowTemps.push(parseInt(jsonObject.list[i].main.temp_min - 273));
                highTemps.push(parseInt(jsonObject.list[i].main.temp_max - 273));
            }
        }
    
        if (idx === 0) {
            // Next day
            searchCityNextDayIcon.src =  './images/weather-icons/'+ jsonObject.list[index].weather[0].main + '.png';
            searchCityNextDayIcon.alt = jsonObject.list[index].weather[0].main;
            searchCityNextDayDate.innerHTML = date.getDate() + " " + monthNames[date.getMonth()];
            searchCityNextDayWeather.innerHTML = jsonObject.list[index].weather[0].main; 
            searchCityNextDayMMinMaxTemp.innerHTML = Math.max(...highTemps) + "° / " + Math.min(...lowTemps) + "°";
            citySearchTemperature.innerHTML = parseInt(jsonObject.list[index].main.temp - 273) + "°";
            date.setDate(date.getDate() + 1);
        }
        else {
            // Next day
            searchCity2ndDayIcon.src =  './images/weather-icons/'+ jsonObject.list[index].weather[0].main + '.png';
            searchCity2ndDayIcon.alt = jsonObject.list[index].weather[0].main;
            searchCity2ndDayDate.innerHTML = date.getDate() + " " + monthNames[date.getMonth()];
            searchCity2ndDayWeather.innerHTML = jsonObject.list[index].weather[0].main; 
            searchCity2ndDayMMinMaxTemp.innerHTML = Math.max(...highTemps) + "° / " + Math.min(...lowTemps) + "°";
            citySearchTemperature.innerHTML = parseInt(jsonObject.list[index].main.temp - 273) + "°";
            date.setDate(date.getDate() - 2);
        }
    }
    setUpChart();
  }

/* Chart */
function setUpChart() {
  new Chart(
    document.getElementById('temperatures'),
    {
      type: 'line',
      data: {
        labels: chartData.map(row => row.day),
        datasets: [
          {
            label: 'Temperatures by day',
            data: chartData.map(row => row.temp),
            fill: false,
            borderColor: 'rgb(62, 205, 249)',
            tension: 0.1
          }
        ]
      }
    }
  );
}