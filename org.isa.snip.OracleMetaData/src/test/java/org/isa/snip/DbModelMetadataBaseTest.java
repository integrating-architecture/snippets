/*Copyright by www.integrating-architecture.de

Use and redistribution in source and binary forms,
with or without modification, are permitted WITHOUT restriction of any kind.
     
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE LIABLE 
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package org.isa.snip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.isa.snip.dbmodel.DbColumnMetadata;
import org.isa.snip.dbmodel.DbTableMetadata;
import org.junit.jupiter.api.Test;

/**
 */
public class DbModelMetadataBaseTest {

	/**
	 */
	public DbModelMetadataBaseTest() {
	}
	
	@Test
	public void testInstantiation()  {
        DbTableMetadata lTable;
        DbColumnMetadata lColumn;
        
        lTable = new DbTableMetadata("User");
        lColumn = new DbColumnMetadata(lTable.getName(), "userid");

        
        assertEquals("User", lTable.getName());
        
        assertEquals("userid", lColumn.getName());
        assertEquals("User", lColumn.getTableName());
        assertEquals("User.userid", lColumn.getQualifiedName());

	}

}
