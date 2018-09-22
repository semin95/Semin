import {OnInit} from '@angular/core';
import {Chart} from 'angular-highcharts';

const statusPodnesen = 'Podnesen';
const statusInProgress = 'U toku';
const statusResolved = 'Rijesen';
const statusClosed = 'Zatvoren';
const noDataString = 'Nema podataka za prikaz';
const chartColors = ['green', '#c2185b', '#61BDE5', '#FFDB00'];

export abstract class StatistikaComponent implements OnInit {
  title;
  reportTypes = ['Mjesečni', 'Godišnji'];
  years = [2015, 2016, 2017, 2018];
  months = [
    {value: 1, viewValue: 'Januar'},
    {value: 2, viewValue: 'Februar'},
    {value: 3, viewValue: 'Mart'},
    {value: 4, viewValue: 'April'},
    {value: 5, viewValue: 'Maj'},
    {value: 6, viewValue: 'Juni'},
    {value: 7, viewValue: 'Juli'},
    {value: 8, viewValue: 'Avgust'},
    {value: 9, viewValue: 'Septembar'},
    {value: 10, viewValue: 'Oktobar'},
    {value: 11, viewValue: 'Novembar'},
    {value: 12, viewValue: 'Decembar'}
  ];

  selectedYear;
  selectedMonth;
  selectedReportType;
  buttonEnabled = false;

  pieChart = new Chart({
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Mjesečni izvještaj'
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    colors: chartColors,
    lang: {
      noData: noDataString
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: false
        },
        showInLegend: true
      }
    },
    credits: {
      enabled: true
    }
  });

  columnChart = new Chart({
    chart: {
      type: 'column'
    },
    title: {
      text: 'Godišnji izvještaj'
    },
    credits: {
      enabled: false
    },
    xAxis: {
      categories: [
        'Januar',
        'Februar',
        'Mart',
        'April',
        'Maj',
        'Juni',
        'Juli',
        'Avgust',
        'Septembar',
        'Oktobar',
        'Novembar',
        'Decembar'
      ],
      crosshair: true
    },
    yAxis: {
      min: 0,
      title: {
        text: 'Ukupan broj'
      }
    },
    colors: chartColors,
    lang: {
      noData: noDataString
    },
    plotOptions: {
      column: {
        pointPadding: 0.2,
        borderWidth: 0
      }
    }
  });

  constructor(private service: StatsService) {
  }

  abstract ngOnInit(): void;

  generateReport(): void {
    switch (this.selectedReportType) {
      case this.reportTypes[0]:
        this.generateMonthlyReport();
        break;
      case this.reportTypes[1]:
        this.generateYearlyReport();
        break;
    }
  }

  generateYearlyReport(): void {
    while (this.columnChart.ref.series.length) {
      this.columnChart.removeSerie(this.columnChart.ref.series.length - 1);
    }

    this.service.getStats(this.selectedYear, null).subscribe(data => {
        let podnesenItems, inProgressItems, resolvedItems, closedItems;
        podnesenItems = new Array(12).fill(0);
        inProgressItems = new Array(12).fill(0);
        resolvedItems = new Array(12).fill(0);
        closedItems = new Array(12).fill(0);
        data.forEach(function (element) {
          switch (element.status) {
            case statusPodnesen:
              podnesenItems[element.month - 1] = element.count;
              break;
            case statusInProgress:
              inProgressItems[element.month - 1] = element.count;
              break;
            case statusResolved:
              resolvedItems[element.month - 1] = element.count;
              break;
            case statusClosed:
              closedItems[element.month - 1] = element.count;
              break;
          }
        });

        this.columnChart.addSerie({
          name: statusPodnesen,
          data: podnesenItems
        });
        this.columnChart.addSerie({
          name: statusInProgress,
          data: inProgressItems
        });
        this.columnChart.addSerie({
          name: statusResolved,
          data: resolvedItems
        });
        this.columnChart.addSerie({
          name: statusClosed,
          data: closedItems
        });
      }
    );
  }

  generateMonthlyReport(): void {
    this.pieChart.removeSerie(0);
    this.addSerieToPieChart();
  }

  private addSerieToPieChart(): void {
    const seriesData = [];
    this.service.getStats(this.selectedYear, this.selectedMonth).subscribe(data => {
        if (Array.isArray(data) && data.length) {
          data.forEach(function (element) {
            seriesData.push({
              name: element.status,
              y: element.count
            });
          });
        }

        this.pieChart.addSerie({
          name: 'Procentualno',
          data: seriesData
        }, true);
      }
    );
  }

  onSelectReportType(reportType: string): void {
    this.selectedReportType = reportType;
  }
}
