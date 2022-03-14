  var HOME_PATH = window.HOME_PATH || '.';

    var latlng = document.querySelector(".map_latlng").value;
    var lat = Number(latlng.substring(latlng.indexOf(':')+1, latlng.indexOf(',')));
    var lng = Number(latlng.substring(latlng.indexOf(':',7)+1, latlng.indexOf(')')));

var center = new naver.maps.LatLng(lat, lng),
    map = new naver.maps.Map('fost_map', {
        center: center.destinationPoint(0, -300),
        zoom: 15
    }),
    marker = new naver.maps.Marker({
        map: map,
        position: center
    });

var fosting_name = document.querySelector(".fost_Name").innerText;
var fosting_category = document.querySelector(".fost_info span:first-child").innerText;

var contentString = [
        '<div class="iw_inner">',
        `   <h3>${fosting_name}</h3>`,
        `   <p>${fosting_category}</p>`,
        '</div>'
    ].join('');

var infowindow = new naver.maps.InfoWindow({
    content: contentString
});

naver.maps.Event.addListener(marker, "click", function(e) {
    if (infowindow.getMap()) {
        infowindow.close();
    } else {
        infowindow.open(map, marker);
    }
});

infowindow.open(map, marker);

