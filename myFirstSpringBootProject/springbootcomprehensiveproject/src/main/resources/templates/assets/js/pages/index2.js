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

Morris.Area({
    element: 'm_area_chart2',
    data: [{
            period: '4.1',
            SiteA: 10,
            SiteB: 20

        }, {
            period: '4.2',
            SiteA: 11,
            SiteB: 19

        }, {
            period: '4.3',
            SiteA: 13,
            SiteB: 17

        }, {
            period: '4.4',
            SiteA: 15,
            SiteB: 23

        }, {
            period: '4.5',
            SiteA: 16,
            SiteB: 21

        }, {
            period: '4.6',
            SiteA: 11,
            SiteB: 12

        }
    ],
    xkey: 'period',
    ykeys: ['SiteA', 'SiteB'],
    labels: ['低温', '高温'],
    pointSize: 0,
    fillOpacity: 0.4,
    pointStrokeColors: ['#b6b8bb', '#a890d3'],
    behaveLikeLine: true,
    gridLineColor: '#e0e0e0',
    lineWidth: 0,
    smooth: true,
    hideHover: 'auto',
    lineColors: ['#b6b8bb', '#a890d3'],
    resize: true

});

//===============================================================================
$(".dial1").knob();
$({animatedVal: 0}).animate({animatedVal: 66}, {
    duration: 4000,
    easing: "swing", 
    step: function() { 
        $(".dial1").val(Math.ceil(this.animatedVal)).trigger("change"); 
    }
});
$(".dial2").knob();
$({animatedVal: 0}).animate({animatedVal: 26}, {
    duration: 4500,
    easing: "swing", 
    step: function() { 
        $(".dial2").val(Math.ceil(this.animatedVal)).trigger("change"); 
    }
});
$(".dial3").knob();
$({animatedVal: 0}).animate({animatedVal: 76}, {
    duration: 3800,
    easing: "swing", 
    step: function() { 
        $(".dial3").val(Math.ceil(this.animatedVal)).trigger("change"); 
    }
});
$(".dial4").knob();
$({animatedVal: 0}).animate({animatedVal: 88}, {
    duration: 5200,
    easing: "swing", 
    step: function() { 
        $(".dial4").val(Math.ceil(this.animatedVal)).trigger("change"); 
    }
});