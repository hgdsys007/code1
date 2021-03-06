function showPaginationDetail(){
	return false;
}

function getQueryParams() {
	var date = $('#search-date').val();
	var yesterday = new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000).format("yyyyMMdd");
	date = date.replace(/-/g, "");
	if (!date || date > yesterday) {
		date = yesterday;
		showMessage("请选择小于今天的日期！");
	}
	var data = {
		"recordDate" : date.substr(0, 6)
	};
	var conditions = {};
	conditions["date.eq"] = parseInt(date);
	var deviceId = $("#search-deviceId").val();
	if (deviceId && query) {
		conditions["deviceId.eq"] = deviceId;
	}
	data["conditions"] = conditions;
	return data;
}

function getQueryUrl() {
	return report_api_server_servlet_path + "/query/alarmDayReport.json?countable=true&orderBy=deviceId&desc=true";
}

function getColumns() {
	return [ [
			{
				field : "deviceName",
				title : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车牌号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					var deviceId = row.deviceId;
					return getDeviceName(deviceId);
				}
			},
			{
				field : "total",
				title : "总计",
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					var total = 0;
					for (var i = 0; i < 50; i++) {
						var val = row["a" + i];
						if (val) {
							total += val;
						}
					}
					return total;
				}
			},
			{
				field : "a32",
				title : "原地设防",
				align : 'center',
				valign : 'middle',
			},
			{
				field : "a33",
				title : "围栏告警",
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					var total = 0;
					var a = [ 34, 33, 20 ];
					for (var i = 0; i < a.length; i++) {
						var val = row["a" + a[i]];
						if (val) {
							total += val;
						}
					}
					return total;
				}
			},
			{
				field : "a36",
				title : "超速告警",
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					var total = 0;
					var a = [ 35, 36, 39, 1 ];
					for (var i = 0; i < a.length; i++) {
						var val = row["a" + a[i]];
						if (val) {
							total += val;
						}
					}
					return total;
				}
			},
			{
				field : "a37",
				title : "路线告警",
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					var total = 0;
					var a = [ 21, 23, 37, 38 ];
					for (var i = 0; i < a.length; i++) {
						var val = row["a" + a[i]];
						if (val) {
							total += val;
						}
					}
					return total;
				}
			},
			{
				field : "a2",
				title : "疲劳驾驶",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a0",
				title : "紧急报警",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a11",
				title : "摄像头故障",
				align : 'center',
				valign : 'middle',
				width : "20px"
			},
			{
				field : "a25",
				title : "油量异常",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a26",
				title : "车辆被盗",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a27",
				title : "非法点火",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a30",
				title : "侧翻预警",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a29",
				title : "碰撞预警",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a31",
				title : "非法开车门",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a7",
				title : "终端主电源欠压",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "a24",
				title : "车辆VSS",
				align : 'center',
				valign : 'middle'
			},
			{
				field : "other",
				title : "其他",
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					var total = 0;
					for (var i = 0; i < 50; i++) {
						var val = row["a" + i];
						if (val) {
							total += val;
						}
					}
					var a = [ 1, 32, 33, 34, 35, 36, 37, 38, 39, 2, 0, 11, 21,
							23, 25, 26, 27, 30, 29, 31, 7, 24, 20 ];
					for (var i = 0; i < a.length; i++) {
						var val = row["a" + a[i]];
						if (val) {
							total -= val;
						}
					}
					return total;
				}
			} ] ];
}

function exportExcel() {
	var date = $('#search-date').val();
	var yesterday = new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000).format("yyyyMMdd");
	date = date.replace(/-/g, "");
	if (!date || date > yesterday) {
		date = yesterday;
		showMessage("请选择小于今天的日期！");
	}
	var deviceId = "";
	if (query) {
		deviceId = $("#search-deviceId").val();
	}
	var url = report_api_server_servlet_path + "/excel/dailyAlarm/" + date
			+ ".json?deviceId=" + deviceId;
	var mediaUrl = getContextPath() + "/attachment/download.do?url=" + url;
	window.open(mediaUrl);
}