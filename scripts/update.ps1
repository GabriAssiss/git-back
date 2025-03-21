$Path = "$PWD/src/back/src"
$FileFilter = "*.java"
$Timeout = 1000
$IncludeSubfolders = $true
$AttributeFilter = [IO.NotifyFilters]::FileName, [IO.NotifyFilters]::LastWrite
$ChangeTypes = [System.IO.WatcherChangeTypes]::Created, [System.IO.WatcherChangeTypes]::Deleted, [System.IO.WatcherChangeTypes]::Changed, [System.IO.WatcherChangeTypes]::Renamed

$Watcher = New-Object IO.FileSystemWatcher -Property @{
	Path = $Path
	Filter = $FileFilter
	IncludeSubdirectories = $IncludeSubfolders
	NotifyFilter = $AttributeFilter
}

try {
	Write-Host "Starting Updater in $Path"

	do {
		$Result = $Watcher.WaitForChanged($ChangeTypes, $Timeout)
		if($Result.TimedOut) {continue}

		$TCPConnection = New-Object System.Net.Sockets.TcpClient("localhost", 8081)
		$Writer = New-Object System.IO.StreamWriter($TCPConnection.GetStream())

		Write-Host "Change detected, updating source"
		$Writer.Write("update")

		$Writer.Close()
		$TCPConnection.CLose()
	} while($true)
} catch [System.Net.Sockets.SocketException] {
	Write-Host "Não foi possível se conectar ao docker"
} finally {
	$Watcher.Dispose()

	Write-Host "Stopping Updater"
}