import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'my-map',
  template: `<div id="map" style="width: 100vw; height: 100vh"></div>`,
})
export class MapComponent implements OnInit {
  ngOnInit(): void {

    ymaps.ready(init);
    var myMap, myPlacemark, searchControl;

    function init() {
      var myMap = new ymaps.Map('map', {
        center: [55.753994, 37.622093],
        zoom: 13
      }, {
        searchControlProvider: 'yandex#search'
      });

      // Поиск станций метро.
      ymaps.geocode(myMap.getCenter(), {
        /**
         * Опции запроса
         * @see https://api.yandex.ru/maps/doc/jsapi/2.1/ref/reference/geocode.xml
         */
        // Ищем только станции метро.
        kind: 'metro',
        // Запрашиваем не более 20 результатов.
        results: 5
      });
    }
  }
}
