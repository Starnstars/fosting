var HOME_PATH = window.HOME_PATH || '.';

var map_latlng = document.querySelectorAll(".map_latlng");

var fosting_content = document.querySelectorAll(".fosting_content");
const HIDDEN_KEY = "hidden";

var MARKER_SPRITE_POSITION = [];

for(let i=0; i<map_latlng.length; i++){
    var map_lat = Number(map_latlng[i].defaultValue.substring(map_latlng[i].defaultValue.indexOf(':')+1, map_latlng[i].defaultValue.indexOf(',')));
    var map_lng = Number(map_latlng[i].defaultValue.substring(map_latlng[i].defaultValue.indexOf(':',7)+1, map_latlng[i].defaultValue.indexOf(')')));

    MARKER_SPRITE_POSITION.push({lat : map_lat,lng : map_lng})
    }

var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.5666805, 126.9784147),
    zoom: 16
});

var markers = [];
for (let key= 0; key<MARKER_SPRITE_POSITION.length;key++) {

    var position = new naver.maps.LatLng(
        MARKER_SPRITE_POSITION[key].lat,
        MARKER_SPRITE_POSITION[key].lng);

    var marker = new naver.maps.Marker({
        map: map,
        position: position,
        title: key,
        icon: {
            url: HOME_PATH +'/img/example/sp_pins_spot_v3.png',
            size: new naver.maps.Size(24, 37),
            anchor: new naver.maps.Point(12, 37),
            origin: new naver.maps.Point(MARKER_SPRITE_POSITION[key][0], MARKER_SPRITE_POSITION[key][1])
        },
        zIndex: 100
    });

    markers.push(marker);
};

naver.maps.Event.addListener(map, 'idle', function() {
    updateMarkers(map, markers);
});
updateMarkers(map, markers);
//먼저 한번 부르면 알아서 사라지지않을까?
//정답

function updateMarkers(map, markers) {

    var mapBounds = map.getBounds();
    var marker, position;

    for (var i = 0; i < markers.length; i++) {

        marker = markers[i]
        position = marker.getPosition();

        if (mapBounds.hasLatLng(position)) {
            showMarker(map, marker);
            fosting_content[i].classList.remove(HIDDEN_KEY);
        } else {
            hideMarker(map, marker);
            fosting_content[i].classList.add(HIDDEN_KEY);
        }
    }
}

function showMarker(map, marker) {

    if (marker.getMap()) return;
    marker.setMap(map);
}

function hideMarker(map, marker) {

    if (!marker.getMap()) return;
    marker.setMap(null);
}


var fosting_name = document.querySelectorAll(".fosting_content ul li:first-child");
var fosting_url = document.querySelectorAll(".fosting_content a");
var fosting_category = document.querySelectorAll(".res_category");

var contentString =[];
var infowindow =[];

for(let i=0; i<markers.length;i++){
    contentString.push( '<div class="iw_inner">'+
                        `   <h3>${fosting_name[i].innerText}</h3>`+
                        `   <p>${fosting_category[i].value}</p>`+
                        `   <a href=${fosting_url[i].href}>더보기</a>`+
                        '</div>');
    infowindow.push( new naver.maps.InfoWindow({
        content: contentString[i]
    }));

    naver.maps.Event.addListener(markers[i], "click", function(e) {
        if (infowindow[i].getMap()) {
            infowindow[i].close();
        } else {
            infowindow[i].open(map, markers[i]);
        }
    });
}