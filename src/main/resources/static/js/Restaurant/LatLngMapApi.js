var map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(37.5666103, 126.9783882),
        zoom: 16
    }),
    infoWindow = null;

function initGeocoder() {

    var latlng = map.getCenter();

    infoWindow = new naver.maps.InfoWindow({
        content: ''
    });

    map.addListener('click', function(e) {
        var latlng = e.coord;

        var latlngInput = document.querySelector("#resLatLng");
        latlngInput.value = latlng;

        infoWindow.setContent([
            '<div style="padding:10px;width:100%;font-size:14px;line-height:20px;">',
            `<strong>이 위치가 맞나요?</strong>`,
            '</div>'
        ].join(''));

        infoWindow.open(map, latlng);
    });
}

naver.maps.onJSContentLoaded = initGeocoder;