
function onLoad() {
	
	    /*<![CDATA[*/
	    var tagMapNodes = [[${tagMapNodes}]];
	    console.log(tagMapNodes);
	    /*]]>*/

	var graph = {
	  "nodes":[
		{"name":"필통","group":1},
		{"name":"가죽 필통","group":2},
		{"name":"저렴이","group":3},
		{"name":"나이키","group":4},
		{"name":"아디다스","group":5},
		{"name":"나이키광고","group":1},
		{"name":"광고","group":2},
		{"name":"운동화","group":3},
		{"name":"안경","group":4},
		{"name":"수첩","group":5},
		{"name":"책","group":1},
		{"name":"텀블러","group":2},
		{"name":"포크","group":3},
		{"name":"샐러드","group":4},
		{"name":"로션","group":5},
		{"name":"모니터","group":1},
		{"name":"숟가락","group":2},
		{"name":"아이폰","group":3},
		{"name":"커피","group":4}
	  ],
	  "links":[
		{"source":1,"target":0,"value":1},
		{"source":2,"target":0,"value":8},
		{"source":5,"target":0,"value":1},
		{"source":8,"target":0,"value":2},
		{"source":11,"target":10,"value":1},
		{"source":11,"target":2,"value":3},
		{"source":11,"target":0,"value":5},
		{"source":12,"target":11,"value":1},
		{"source":15,"target":11,"value":1},
		{"source":17,"target":16,"value":4},
		{"source":18,"target":16,"value":4},
		{"source":18,"target":17,"value":4}
	  ]
	};
	var width = window.innerWidth,
		height = window.innerHeight * 0.7;
	
	var color = d3.scale.category20c();
	
	// 캔버스 모양
	var force = d3.layout.force()
		.charge(-600)
		.linkDistance(100)
		.size([width, height]);
	
	// 캔버스 위치, 크기
	var svg = d3.select("#graph1").append("svg")
		.attr("width", width)
		.attr("height", height);
	
	// 노드 제작, 링크 연결
	var drawGraph = function(graph) {
	  force
		.nodes(graph.nodes)
		.links(graph.links)
		.start();
	
	// 연결선  
	var link = svg.selectAll(".link")
		.data(graph.links)
		.enter().append("line")
		.attr("class", "link")
		.style("stroke-width", function(d) { return Math.sqrt(d.value); });	// 선 굵기
	
	// svg g그룹의 gnode(원)
	var gnodes = svg.selectAll('g.gnode')
		.data(graph.nodes)
		.enter()
		.append('g')
		.classed('gnode', true);
	
	function randomNum(min, max){
		var randNum = Math.floor(Math.random()*(max-min+1)) + min;
		return randNum; 
	}
	
	// 노드 모양
		var node = gnodes.append("circle")
			.attr("class", "node")
			.attr("r", function(d) { return d.group * 10; })
   	        .style("fill", function(d) { return color(d.group) ; })
			.style("stroke-width", function(d) { return d.group * 2})
			.call(force.drag);
	
	// 태그 타이틀
	var labels = gnodes.append("text")
	    .text(function(d) { return d.name; })
	  	.attr("x", -12)
	    .attr("y", 5);
	
	console.log(labels);
		
	force.on("tick", function() {
			link.attr("x1", function(d) { return d.source.x; })
				.attr("y1", function(d) { return d.source.y; })
				.attr("x2", function(d) { return d.target.x; })
				.attr("y2", function(d) { return d.target.y; });
		
			gnodes.attr("transform", function(d) { 
				return 'translate(' + [d.x, d.y] + ')'; 
			});
		  
		
		  
		});
	};
	
	
	
	drawGraph(graph);	
	
	
}