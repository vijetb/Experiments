$(document).ready(function () {
    var projects = [
        {
            projectId:"projWHAM",
            projHeading:"What's Happening Around Me (WHAM)!",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC, JSON",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["contribution-1","contribution-2","contribution-3"],
            projectAwards:["award-1","award-2","award-3"]
        },
        {
            projectId:"projHushVault",
            projHeading:"HushVault!",
            projOrgn:"-",
            projDuration:"January 2016 - April 2016",
            projTech:"Android",
            projGitHubLink:null,
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["contribution-1","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projT2T",
            projHeading:"Trash-2-Treasure!",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projIR",
            projHeading:"Information Retrieval Projects!",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projOSGi",
            projHeading:"Apache Felix OSGi Tutorial!",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projWebsite",
            projHeading:"Personal Website",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projDocGenerator",
            projHeading:"Doc Generator",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projViewColorPalette",
            projHeading:"View Color Palette",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projFaultAlarm",
            projHeading:"Fault Notification Alarm in Network Management System",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projHangman",
            projHeading:"Hangman",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        },
        {
            projectId:"projConverter",
            projHeading:"Converter",
            projOrgn:"Northeastern University",
            projDuration:"January 2016 - April 2016",
            projTech:"Java, Spring MVC, JDBC",
            projGitHubLink:"www.github.com",
            projLiveProjLink:"www.github.com",
            projDocumentation:"www.github.com",
            projDesc:["paragraph-1","paragraph-2"],
            projContributions:["W3Schools is optimized for learning, testing, and training. Examples might be simplified to improve reading and basic understanding. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using this site","contribution-2","contribution-3"],
            projectAwards:null
        }
    ];

    var projHash = window.location.hash.split("#")[1];

    for(project in projects){
        if(projects[project].projectId === projHash){
            //project heading
            $( "#projHeading").text(projects[project].projHeading);
            //organization
            $("#projOrgn").text(projects[project].projOrgn);
            $("#projDuration").text(projects[project].projDuration);
            $("#projTech").text(projects[project].projTech);

            if(projects[project].projGitHubLink === null){
                $("#githubLinkSection").remove();
            }else{
                $("#projGitHubLink").attr("href", projects[project].projGitHubLink);
            }

            if(projects[project].projLiveProjLink === null){
                $("#liveLinkSection").remove();
            }else{
                $("#projLiveProjLink").attr("href", projects[project].projLiveProjLink);
            }

            if(projects[project].projDocumentation === null){
                $("#projDocSection").remove();
            }else{
                $("#projDocumentation").attr("href", projects[project].projDocumentation);
            }

            for (i in  projects[project].projDesc){
                $("#projDesc").append("<p>"+ projects[project].projDesc[i]+"</p>");
            }


            for (i in  projects[project].projContributions){
                $("#projContributions").append("<p class='black-color'><span class='glyphicon glyphicon-triangle-right proj-desc-contribution'></span>&nbsp;&nbsp;"+ projects[project].projContributions[i]+"</p>");
            }


            if(projects[project].projectAwards === null){
                $("#awardRow").remove();
            }else{
                for (i in  projects[project].projectAwards){
                    $("#projectAwards").append("<p class='black-color'><i class='icon ion-trophy edu-trophy'></i></span> &nbsp;&nbsp;"+ projects[project].projectAwards[i]+"</p>");
                }
            }

            break;
        }
    }




    $( ".projects-btn" ).click(function() {
        window.location.href = "index.html#projectPage";
    });


});