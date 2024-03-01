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

package org.isa.snip.dbmodel;

/**
 */
public class DbColumnMetadata extends AbstractDbMetadata {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private String tableName = "";
	private String qualifiedName = "";
	
	/**
	 */
	public DbColumnMetadata(String pTableName, String pName) {
		super(pName);
		tableName = pTableName;
		qualifiedName = tableName+"."+pName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the qualifiedName
	 */
	public String getQualifiedName() {
		return qualifiedName;
	}

}
