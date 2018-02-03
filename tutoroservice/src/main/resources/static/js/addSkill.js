$(document).ready(function() {
    $('#skill_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    stringLength: {
                        min: 2,
                        max: 15,
                    },
                    notEmpty: {
                        message: 'Please supply your first name'
                    }
                }
            },
            description: {
                validators: {
                    stringLength: {
                        max: 255,
                        message: 'Max 255 letters'
                    },
                    notEmpty: {
                        message: 'Please supply your skill`s description'
                    }
                }
            }

        }
    })

});