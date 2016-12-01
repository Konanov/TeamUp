import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'my-map',
  template: `<div id="map" style="width: 100vw; height: 100vh"></div>`,
})
export class MapComponent implements OnInit {
  ngOnInit(): void {

    ymaps.ready(init);
    var map, myPlacemark, searchControl;

    function init(){
      var map = new ymaps.Map ("map", {
        center: [55.76, 37.64],
        zoom: 13,
        behaviors: ['ruler', 'scrollZoom', 'drag']
      });

      myPlacemark = new ymaps.Placemark([55.76, 37.64], { content: 'Москва!', balloonContent: 'Столица России' });

      map.geoObjects.add(myPlacemark);

      searchControl = new ymaps.control.SearchControl({
        options: {
          float: 'right',
          floatIndex: 100,
          noPlacemark: true,
          provider: 'yandex#search',
          useMapBounds: true,
          strictBounds: true
        }
      });
      map.controls.add(searchControl);

      /*var myGeocoder = ymaps.geocode(
        /!* Строка с адресом, который нужно
         геокодировать *!/
        "Москва", {
          /!* Опции поиска:
           - область поиска *!/
          boundedBy: map.getBounds(),
          // Запрашиваем не более 20 результатов.
          results: 5
        });

      /!* После того, как поиск вернул результат, вызывается
       callback-функция *!/
      myGeocoder.then(function (res) {
        /!* Размещение полученной коллекции
         геообъектов на карте *!/
        map.geoObjects.add(res.geoObjects);
      });*/
    }
  }
}
