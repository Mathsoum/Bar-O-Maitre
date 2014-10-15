Welcome, coders !
=================

This document is organized as follow :

* [Git branching](welcome.md#git-branching)
* [Development and integration workflow](welcome.md#development-and-integration-workflow)
* [Contacts & co.](welcome.md#contacts--co)

Git branching
----------------------------------
Every time you want to create a branch, you must name it matching the
folowing pattern :

`/<quad>/<US-#>/<US slug>/[<modifier>/]<#num>`

In other words :

* `<quad>` : Short for your name : John Smith ==> jsmi
* `<US-#>` : The US tag from Github (like US-1, US-2, etc.)
* `<US-slug>` : Slug is a short code name for the US. If the story is "Integrate something with the DB app" then the slug could be "integration-something-db" for example.
* `<modifier>` : An optional status modifier. For example, `ready` means that the branch is reviewed and ready for integration into master. You shouldn't use any modifier when you start a new branch.
* `<#num>` : An integer counter like auto-increment in DB. Must start with 1 (not 0)

Following these requirements, there will logically be a branch for each US. Feel free to fork an existing branch created from an other developer to improve it or make suggestions. Don't forget to add your `<quad>` as a prefix of the new branch if you do so.

Development and integration workflow
-------------------------------------------------------
Here is the 'traditional' development workflow. You should follow it while you'll develop a US.

1. Create a branch by forking master in priority, unless you have to start from an other US not already integrated. If you do, please keep a track from where you start. This will facilitate integration if the branch you're based on has evolved since you've forked from it. The new branch you're creating should be named according with requirements explained before in the document (see [Git branching](welcome.md#git-branching) section).

2. During the development phase, you can do as many commits as you want. The history of your work will be rewrite before integration to master. So don't worry about doing dumb commits, there won't be visible in the main branch ;) This system allow you to keep a trace of what you've done without flooding master branch history.

3. Also during the development phase, some work may be integrated to master, causing your branch to be based on an outdated version of the master branch. This can be solved using the `git rebase` command. For the clarity of this document, you're greatly advised to look for documentation about this command. Also, the team could help you with that, don't be afraid to ask =)

4. Some day, your work will be done. A US is qualified as 'done' when all features have been developed, all tests passes and the code is clean regarding to SonarQube analysis.

5. Then you must rewrite your history before integration to master. Here comes the `ready` modifier. Once you've rewrite your history and your branch is clean, you can (and must) tag it with `ready` as explained before in the document (see [Git branching](welcome.md#git-branching) section). Rewriting your history can also be done using `git rebase` in interactive mode. This allows you to merge commits with the same meaning, change commit messages, and lot of usefull stuff. Again, the git documentation is your friend as well as the memebers of the team.

6. Thus, you or someone more familiar with branch integration using git will integrate your work to master.

7. Thanks for your contribution, keep rocking o/

Contacts & co.
------------------------------------
If you have any question, feel free to ask a memeber of the team :

* Joris MICHEL			`joris.michel at master-developpement-logiciel dot fr`
* Mathieu MOREAU	`mathieu.moreau at master-developpement-logiciel dot fr`
* Léo MOULY			`leo.mouly at master-developpement-logiciel dot fr`
* Mathieu SOUM		`mathieu.soum at master-developpement-logiciel dot fr`
