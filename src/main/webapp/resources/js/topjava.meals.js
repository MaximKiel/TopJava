const mealAjaxUrl = "meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});

function filterByDateTime() {
    let filterFrom = $('#dateTimeFilter');
    $.ajax({
        type: "GET",
        url: ctx.ajaxUrl + "filter",
        data: {
            startDate: filterFrom.find("#startDate"),
            startTime: filterFrom.find("#startTime"),
            endDate: filterFrom.find("#endDate"),
            endTime: filterFrom.find("#endTime")
        }
    }).done(function () {
        ctx.datatableApi.clear().rows.add(data).draw();
        successNoty("Filtered")
    });
}