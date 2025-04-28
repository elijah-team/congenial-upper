Change-id Commit hook
----------------------

Got it from:

https://raw.githubusercontent.com/GerritCodeReview/gerrit/36991d7fbff445518c819aa7c6e05bb0e4ed2daa/resources/com/google/gerrit/server/tools/root/hooks/commit-msg-legacy


cd .git/hooks
rm *sample # nit
wget $url
mv commit-msg-legacy commit-msg
chmod +x commit-msg

That's it!!
