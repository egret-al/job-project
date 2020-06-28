$(function() {
    console.log("开始加载");
    "use strict";
    MorrisArea();
    Jknob();
});

//==================================大图图表内容，由ajax请求获取数据=============================================
function MorrisArea() {
    Morris.Area({
        element: 'area_chart',
        data: [{
            period: '2011',
            Project1: 2,
            Project2: 0,
            Project3: 0
        }, {
            period: '2012',
            Project1: 50,
            Project2: 15,
            Project3: 5
        }, {
            period: '2013',
            Project1: 15,
            Project2: 50,
            Project3: 23
        }, {
            period: '2014',
            Project1: 45,
            Project2: 12,
            Project3: 7
        }, {
            period: '2015',
            Project1: 20,
            Project2: 32,
            Project3: 55
        }, {
            period: '2016',
            Project1: 39,
            Project2: 67,
            Project3: 20
        }, {
            period: '2017',
            Project1: 20,
            Project2: 9,
            Project3: 5
        }

    ],
    lineColors: ['#616161', '#00ced1', '#ff758e'],
    xkey: 'period',
    ykeys: ['Project1', 'Project2', 'Project3'],
    labels: ['Project1', 'Project2', 'Project3'],
    pointSize: 0,
    lineWidth: 0,
    resize: true,
    fillOpacity: 0.8,
    behaveLikeLine: true,
    gridLineColor: '#e0e0e0',
    hideHover: 'auto'
    });
}
//===============================================================================
function Jknob() {
    $('.knob').knob({
        draw: function() {
            // "tron" case
            if (this.$.data('skin') == 'tron') {

                var a = this.angle(this.cv) // Angle
                    ,
                    sa = this.startAngle // Previous start angle
                    ,
                    sat = this.startAngle // Start angle
                    ,
                    ea // Previous end angle
                    , eat = sat + a // End angle
                    ,
                    r = true;

                this.g.lineWidth = this.lineWidth;

                this.o.cursor &&
                    (sat = eat - 0.3) &&
                    (eat = eat + 0.3);

                if (this.o.displayPrevious) {
                    ea = this.startAngle + this.angle(this.value);
                    this.o.cursor &&
                        (sa = ea - 0.3) &&
                        (ea = ea + 0.3);
                    this.g.beginPath();
                    this.g.strokeStyle = this.previousColor;
                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
                    this.g.stroke();
                }

                this.g.beginPath();
                this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
                this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
                this.g.stroke();

                this.g.lineWidth = 2;
                this.g.beginPath();
                this.g.strokeStyle = this.o.fgColor;
                this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
                this.g.stroke();

                return false;
            }
        }
    });
}
//===============================================================================
$(window).on('scroll',function() {
    $('.card .sparkline').each(function() {
        var imagePos = $(this).offset().top;

        var topOfWindow = $(window).scrollTop();
        if (imagePos < topOfWindow + 400) {
            $(this).addClass("pullUp");
        }
    });
});
//===============================================================================
// Customized line Index page
$('#linecustom1').sparkline('html',
{
    height: '35px',
    width: '100%',
    lineColor: '#e5d1e4',
    fillColor: '#f3e8f2',
    minSpotColor: true,
    maxSpotColor: true,
    spotColor: '#e2a8df',
    spotRadius: 1
});
$('#linecustom2').sparkline('html',
{
    height: '35px',
    width: '100%',
    lineColor: '#c9e3f4',
    fillColor: '#dfeefa',
    minSpotColor: true,
    maxSpotColor: true,
    spotColor: '#8dbfe0',
    spotRadius: 1
});
$('#linecustom3').sparkline('html',
{	
    height: '35px',
    width: '100%',
    lineColor: '#efded3',
    fillColor: '#f8f0ea',
    minSpotColor: true,
    maxSpotColor: true,
    spotColor: '#e0b89d',
    spotRadius: 1
});