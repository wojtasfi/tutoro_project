$(document).ready(function() {
    $('#contact_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            first_name: {
                validators: {
stringLength: {
                        min: 2,
                    },
                        notEmpty: {
                        message: 'Please supply your first name'
                    }
                }
            },
             last_name: {
                validators: {
                     stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Please supply your last name'
                    }
                }
            },
            login: {
                            validators: {
                                 stringLength: {
                                    min: 2,
                                },
                                notEmpty: {
                                    message: 'Please supply your login'
                                }
                            }
                        },
            password: {
                            validators: {
                                 stringLength: {
                                    min: 2,
                                },
                                notEmpty: {
                                    message: 'Please supply your password'
                                }
                            }
                        },
            email: {
                validators: {
                    notEmpty: {
                        message: 'Please supply your email address'
                    },
                    emailAddress: {
                        message: 'Please supply a valid email address'
                    }
                }
            },
            skype: {
                validators: {
                    notEmpty: {
                        message: 'Please supply your skype name'
                    }

                }
            }





            }
        })

});

